package com.spm.ParkMe.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.managers.AbusiveOccupationManager;
import com.spm.ParkMe.managers.ExpirationManager;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.ParkingLotTicket;
import com.spm.ParkMe.models.PersonalParkingLot;
import com.spm.ParkMe.models.PersonalParkingLotSubscription;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.RefreshTicketInfo;
import com.spm.ParkMe.models.requestBody.SensorChangeInfo;
import com.spm.ParkMe.models.requestBody.StreetInfo;
import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.ParkingLotTicketRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotSubscriptionRepository;
import com.spm.ParkMe.repositories.UserRepository;
import static com.spm.ParkMe.constants.EndpointContants.*;

@RestController
@RequestMapping(DRIVER_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
public class DriverController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private DriverInfoRepository driverRepository;

	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	
	@Autowired
	private HandicapPermitsRequestsRepository handicapRepository;
	
	@Autowired
	private ParkingLotTicketRepository parkingLotTicketRepository;
	
	@Autowired
	private AbusiveOccupationManager abusiveOccupationManager;
	
	@Autowired
	private ExpirationManager expirationManager;
	
	@Autowired
	private NotificationDispatcher notificationDispatcher;
	
	@Autowired
	private PersonalParkingLotRepository personalParkingLotRepository;
	
	@Autowired
	private PersonalParkingLotSubscriptionRepository personalParkingLotSubscriptionRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping(path = DRIVER_REGISTRATION_ENDPOINT, consumes = "application/json")
	public ResponseEntity registration(@Valid @RequestBody Driver driver) throws IOException {
		
		if(!repository.existsByUsername(driver.getEmail())) {
			repository.save(new User(driver.getUsername(), driver.getFirstName(), driver.getLastName(), driver.getSsn(), driver.getPhone(), driver.getEmail(), encoder.encode(driver.getPassword()), Roles.ROLE_DRIVER));
			driverRepository.save(new DriverInfo(driver));
			return new ResponseEntity(HttpStatus.OK);
		}else
		{
			return new ResponseEntity(HttpStatus.CONFLICT);		
			}
	
	}

	@PostMapping(path = DRIVER_HANDICAP_PERMITS_ENDPOINT, consumes = "application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity uploadHandicapPermitsRequest(Authentication authentication) throws IOException {
		String username = authentication.getName();
		DriverInfo driverInfo = driverRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("The user is not registered in DriverInfo collection."));
		// check if he already has handicap set
		if (driverInfo.getHandicap()) {
			return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
		}
		// check if the user has already sent a request
		List<HandicapPermitsRequest> userPendingRequests = handicapRepository.findAll().stream()
				.filter(req -> !req.isProcessed() && req.getUsername().equals(username)).collect(Collectors.toList());
		if (userPendingRequests.size() > 0) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		handicapRepository.save(new HandicapPermitsRequest(username, System.currentTimeMillis(), false, false));
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping(path = DRIVER_STATUS_PARKINGLOT_SET_STATUS_BOOKED, consumes = "application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<?> setStatusParkingLotAsBooked(Authentication authentication, @Valid @RequestBody ParkingLot pl) throws IOException {
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(pl.getStreet(), pl.getNumberOfParkingLot());
		if(!parkingLots.isEmpty()) {
			ParkingLot parkingLot = parkingLots.get(0);
			if(parkingLot.getStatus() != (Status.FREE)) {
				return new ResponseEntity<String>("Parking Lot is already booked or occupied.", HttpStatus.CONFLICT);
			}
			else if(parkingLotBookingRepository.existsByUsername(authentication.getName())) {
				return new ResponseEntity<String>("You already have booked a parking lot. If you want to book a different one, please cancel the current booking.", HttpStatus.CONFLICT);
			}
			else if(!parkingLot.getTypeOfVehicle().equals(driverRepository.findByUsername(authentication.getName()).get().getVehicleType())) {
				return new ResponseEntity<String>("Your vehicle can not be parked in this parking lot.", HttpStatus.CONFLICT);
			}
			else if(parkingLot.getIsHandicapParkingLot() && !driverRepository.findByUsername(authentication.getName()).get().getHandicap()) {
				return new ResponseEntity<String>("You have no permission to park in a hanidcap parking lot.", HttpStatus.CONFLICT);
			}
			/*
			else if(!parkingLotTicketRepository.findByUsername(authentication.getName()).isEmpty()) {
				return new ResponseEntity<String>("You already have bought a ticket. You can book a new parking lot when you will free the current one.", HttpStatus.CONFLICT);
			}
			*/
			else {
				//set parking lot status as booked
				List<ParkingLot> parks = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(),
						parkingLot.getNumberOfParkingLot());
				ParkingLot park = parks.get(0);
				park.setStatus(Status.BOOKED);
				parkingLotRepository.save(park);
				//create a booking object
				ParkingLotBooking booking = new ParkingLotBooking(parkingLot.getStreet(), parkingLot.getNumberOfParkingLot(), authentication.getName(), System.currentTimeMillis(), parkingLot.getCoordinates(), parkingLot.getPricePerHour());
				parkingLotBookingRepository.save(booking);
				return ResponseEntity.ok(new MessageResponse("Parking Lot Successfully Booked"));
			}
		}
		return new ResponseEntity(new MessageResponse("There is no parking lot with street " + pl.getStreet() + " and #" + pl.getNumberOfParkingLot()), HttpStatus.NOT_FOUND);
		

	}

	@PutMapping(path = DRIVER_STATUS_PARKINGLOT_SET_STATUS_FREE, consumes = "application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity setStatusParkingLotAsFree(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		if (parkingLot.getStatus() != Status.FREE) {
			List<ParkingLot> parks = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(),
					parkingLot.getNumberOfParkingLot());
			ParkingLot park = parks.get(0);
			park.setStatus(Status.FREE);
			parkingLotRepository.save(park);
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = DRIVER_STATUS_PARKINGLOT_SET_STATUS_OCCUPIED, consumes = "application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity setStatusParkingLotAsOccupied(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		if (parkingLot.getStatus() == Status.BOOKED) {
			List<ParkingLot> parks = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(),
					parkingLot.getNumberOfParkingLot());
			ParkingLot park = parks.get(0);
			park.setStatus(Status.OCCUPIED);
			parkingLotRepository.save(park);
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = DRIVER_GET_ALL_STREETS)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<List<StreetInfo>> getAllStreetInfos() {
		List<StreetInfo> infos = parkingLotRepository.findAll().stream().map(lot -> lot.getStreet()).distinct()
				.map(street -> new StreetInfo(parkingLotRepository.findByStreet(street).get(0).getStreet(),
						parkingLotRepository.findByStreet(street).get(0).getCoordinates()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(infos);
	}

	@GetMapping(path = DRIVER_GET_CURRENT_BOOKING)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<ParkingLotBooking> getCurrentBooking(Authentication authentication) {
		List<ParkingLotBooking> bookings = parkingLotBookingRepository.findByUsername(authentication.getName());
		if(bookings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(bookings.get(0));
	}
	
	@DeleteMapping(path= DRIVER_DELETE_CURRENT_BOOKING)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity  deleteParkingLotBooking(Authentication authentication){
		String username= authentication.getName();
		List<ParkingLotBooking> bookings= parkingLotBookingRepository.findByUsername(username);
		if(!bookings.isEmpty()) {
			ParkingLotBooking booking= bookings.get(0);
			Integer numberOfParkingLot=booking.getNumberOfParkingLot();
			String street=booking.getStreet();
			parkingLotBookingRepository.delete(booking);
			List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
			ParkingLot parkingLot= parkingLots.get(0);
			parkingLot.setStatus(Status.FREE);
			parkingLotRepository.save(parkingLot);
			return new ResponseEntity(HttpStatus.OK);
		}else
			return new ResponseEntity(HttpStatus.NOT_FOUND); 
	}
		 
		
	private ParkingLot getNearestParkingLotFromCoordinates(List<ParkingLot> parkingLots, double latitude, double longitude) {
		if(parkingLots.isEmpty()) {
			return null;
		}
		double min = 10000000.0;
		int index = 0;
		for(int i = 0; i < parkingLots.size(); i++) {
			double parkLat = Double.parseDouble(parkingLots.get(i).getCoordinates().getLatitude());
			double parkLng = Double.parseDouble(parkingLots.get(i).getCoordinates().getLongitude());
			double distance = Math.hypot(Math.abs(latitude - parkLat), Math.abs(longitude - parkLng));
			if(distance < min) {
				min = distance;
				index = i;
			}
		}
		return parkingLots.get(index);
	}
		
	@GetMapping(path = DRIVER_GET_NEAREST_PARKING_LOT)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<ParkingLot> getNearestParkingLot(Authentication authentication, @NotNull @RequestParam double latitude, @NotNull @RequestParam double longitude) {
		DriverInfo driverInfo = driverRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Driver info Not Found with username: " + authentication.getName()));
		List<ParkingLot> compatibleParkingLots = parkingLotRepository.findCompatibleFreeParkingLots(driverInfo.getHandicap(), driverInfo.getVehicleType());
		ParkingLot nearestParkingLot = this.getNearestParkingLotFromCoordinates(compatibleParkingLots, latitude, longitude);
		if(nearestParkingLot != null) {
			return ResponseEntity.ok(nearestParkingLot);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = DRIVER_CHANGE_PARKING_LOT)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<ParkingLot> changeParkingLot(Authentication authentication) {
		DriverInfo driverInfo = driverRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Driver info Not Found with username: " + authentication.getName()));
		List<ParkingLot> compatibleParkingLots = parkingLotRepository.findCompatibleFreeParkingLots(driverInfo.getHandicap(), driverInfo.getVehicleType());
		//get booked parking lot
		List<ParkingLotBooking> bookings = parkingLotBookingRepository.findByUsername(authentication.getName());
		if(!bookings.isEmpty()) {
			ParkingLotBooking booking = bookings.get(0);
			ParkingLot nearestParkingLot = this.getNearestParkingLotFromCoordinates(compatibleParkingLots, Double.parseDouble(booking.getCoordinates().getLatitude()), Double.parseDouble(booking.getCoordinates().getLongitude()));
			if(nearestParkingLot != null) {
				parkingLotBookingRepository.delete(booking);
				ParkingLot parkingLot = parkingLotRepository.findByStreetAndNumberOfParkingLot(booking.getStreet(), booking.getNumberOfParkingLot()).get(0);
				parkingLot.setStatus(Status.FREE);
				parkingLotRepository.save(parkingLot);
				return ResponseEntity.ok(nearestParkingLot);
			}
			else {
				return new ResponseEntity(new MessageResponse("Unfortunately it seems like there is no compatible parking lot left."), HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity(new MessageResponse("You have no booked parking lot."),HttpStatus.CONFLICT);
		}
		
	}
	
	
	@GetMapping(path = DRIVER_GET_ALL_DRIVER_TICKET_PARKINGLOT)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<List<ParkingLotTicket>> getAllParkingLotTickets(Authentication authentication){
		List<ParkingLotTicket> parkingLotTickets= parkingLotTicketRepository.findByUsername(authentication.getName());
	
			return ResponseEntity.ok(parkingLotTickets);
				
	}
	
	
	@PostMapping(path = DRIVER_POST_CREATE_DRIVER_TICKET_PARKINGLOT, consumes = "application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity createParkingLotTicket(@Valid @RequestBody ParkingLotTicket parkingLotTicket)throws IOException{
		List<ParkingLot> parkingLots=parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLotTicket.getStreet(), parkingLotTicket.getNumberOfParkingLot());
		List<ParkingLotBooking> parkingLotBookings= parkingLotBookingRepository.findByUsername(parkingLotTicket.getUsername());

		if(!parkingLots.isEmpty() && !parkingLotBookings.isEmpty()) {
			ParkingLot parkingLot=parkingLots.get(0);
			parkingLot.setStatus(Status.OCCUPIED);
			parkingLot.setSensorState(SensorState.ON);
			ParkingLotBooking parkingLotBooking= parkingLotBookings.get(0);

			parkingLotBookingRepository.delete(parkingLotBooking);
			parkingLotRepository.save(parkingLot);
			parkingLotTicketRepository.save(parkingLotTicket);
			Thread thread = new Thread(() -> {
				try {
					//(parkingLotTicket.getExpiringTimestamp()-System.currentTimeMillis())- (5*60*1000)
					Thread.sleep(10000);
					expirationManager.sendNotificationToDriverBeforeTicketExpiring(parkingLotTicket.getStreet(), parkingLotTicket.getNumberOfParkingLot(), parkingLotTicket.getUsername());
					Thread.sleep(30000);
					if(parkingLot.getStatus()!= Status.FREE && parkingLotTicket.getExpiringTimestamp() < System.currentTimeMillis()) {
						expirationManager.sendNotificationToVigilantForTicketExpiring(parkingLotTicket.getStreet(), parkingLotTicket.getNumberOfParkingLot());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			thread.start();
			return new ResponseEntity(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(path = DRIVER_SET_SENSOR_PARKINGLOT)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity setParkingLotSensor(@NotNull @RequestBody SensorChangeInfo sensorChangeInfo) {
		List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(sensorChangeInfo.getStreet(), sensorChangeInfo.getNumber());
	
		if(!parkingLots.isEmpty()) {
			
			ParkingLot parkingLot = parkingLots.get(0);
			if(sensorChangeInfo.getState() == SensorState.ON)
			{
				Thread thread = new Thread(() -> {
					try {
						Thread.sleep(10000);
						if(parkingLot.getStatus().equals(Status.BOOKED)) {
							abusiveOccupationManager.sendNotificationToDriver(sensorChangeInfo.getStreet(), sensorChangeInfo.getNumber());
							if(!abusiveOccupationManager.isSolved()) {
								Thread.sleep(10000);
								abusiveOccupationManager.sendNotificationToVigilant(sensorChangeInfo.getStreet(), sensorChangeInfo.getNumber());
							}
						}
						if(parkingLot.getStatus().equals(Status.FREE) || parkingLot.getStatus().equals(Status.DISABLED)) {
							abusiveOccupationManager.sendNotificationToVigilant(sensorChangeInfo.getStreet(), sensorChangeInfo.getNumber());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				thread.start();
			}else {
				if(!parkingLot.getStatus().equals(Status.BOOKED)) {
					if(parkingLot.getStatus().equals(Status.OCCUPIED)) {
						List<ParkingLotTicket> parkingLotTickets= parkingLotTicketRepository.findByStreetAndNumberOfParkingLot(sensorChangeInfo.getStreet(), sensorChangeInfo.getNumber());
						if(!parkingLotTickets.isEmpty()) {
							ParkingLotTicket parkingLotTicket = parkingLotTickets.get(0);
							if(System.currentTimeMillis()<(parkingLotTicket.getExpiringTimestamp()-(5*60*1000))){
								Double refundCalculated =((parkingLotTicket.getExpiringTimestamp()-System.currentTimeMillis())/3600000)*parkingLot.getPricePerHour();
								Notification notification= new Notification( "Money refunded", "You have been refunded "+ refundCalculated+ " Euros.", parkingLotTicket.getUsername(), System.currentTimeMillis() );
								notification.setCategoryNotification(CategoryNotification.DRIVER_REFUNDED_TICKET);
								notificationDispatcher.sendNotificationToUser(parkingLotTicket.getUsername(), notification);
							}
						}
					}
				
					parkingLot.setStatus(Status.FREE);
				}
			}
			
			parkingLot.setSensorState(sensorChangeInfo.getState());
			parkingLotRepository.save(parkingLot);
			return new ResponseEntity(new MessageResponse("Sensor status changed"), HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(path = DRIVER_REFRESH_TICKET)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity createRefreshParkingLotTicket(@NotNull @RequestBody  RefreshTicketInfo refreshTicketInfo) {
		List<ParkingLotTicket> parkingLotTickets= parkingLotTicketRepository.findByStreetAndNumberOfParkingLot(refreshTicketInfo.getStreet(), refreshTicketInfo.getNumberOfParkingLot());
		List<ParkingLot> parkings= parkingLotRepository.findByStreetAndNumberOfParkingLot(refreshTicketInfo.getStreet(), refreshTicketInfo.getNumberOfParkingLot());
		if(!parkingLotTickets.isEmpty() && !parkings.isEmpty()) {
			ParkingLotTicket parkingLotTicket= parkingLotTickets.get(0);
			if(System.currentTimeMillis() < parkingLotTicket.getExpiringTimestamp() ) {
				parkingLotTicket.setExpiringTimestamp(parkingLotTicket.getExpiringTimestamp()+(refreshTicketInfo.getExtraHours()*60*60*1000));
				parkingLotTicketRepository.save(parkingLotTicket);
				ParkingLot park= parkings.get(0);
				Thread thread = new Thread(() -> {
					try {
						//(parkingLotTicket.getExpiringTimestamp()-System.currentTimeMillis())- (5*60*1000)
						Thread.sleep(10000);
						expirationManager.sendNotificationToDriverBeforeTicketExpiring(parkingLotTicket.getStreet(), parkingLotTicket.getNumberOfParkingLot(), parkingLotTicket.getUsername());
						Thread.sleep(10000);
						if(park.getStatus()!= Status.FREE && parkingLotTicket.getExpiringTimestamp() < System.currentTimeMillis()) {
							expirationManager.sendNotificationToVigilantForTicketExpiring(parkingLotTicket.getStreet(), parkingLotTicket.getNumberOfParkingLot());
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				thread.start();
				return new ResponseEntity(new MessageResponse("Ticket refreshed successfully"), HttpStatus.OK);
			}else
			{
				return new  ResponseEntity(new MessageResponse("Ticket is already expired and cannot be refreshed"), HttpStatus.NOT_FOUND);
			}
		}else
		{
			return new ResponseEntity(new MessageResponse("There aren't parkingLots or Tickets"),HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	
	@GetMapping(path = DRIVER_GET_ALL_AVAILABLE_PERSONAL_PARKING_LOTS)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity getAllAvailablePersonalParkingLots() {
		List<PersonalParkingLot> parkingLots = personalParkingLotRepository.findAll();
		List<PersonalParkingLotSubscription> subscriptions = personalParkingLotSubscriptionRepository.findAll();
		List<PersonalParkingLot> result = new ArrayList<PersonalParkingLot>();
		for(PersonalParkingLot parkingLot : parkingLots) {
			for(PersonalParkingLotSubscription subscription : subscriptions) {
				if(parkingLot.getStreet().equals(subscription.getStreet()) && parkingLot.getNumberOfParkingLot() == subscription.getNumberOfParkingLot()) {
					result.add(parkingLot);
				}
			}
		}
		return ResponseEntity.ok(result);
	}
}