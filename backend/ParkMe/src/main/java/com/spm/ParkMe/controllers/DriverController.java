package com.spm.ParkMe.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.ParkingLotTicket;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.StreetInfo;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.ParkingLotTicketRepository;
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
	
	@PostMapping(path = DRIVER_REGISTRATION_ENDPOINT, consumes = "application/json")
	public void registration(@Valid @RequestBody Driver driver) throws IOException {
		repository.save(driver);
		driverRepository.save(new DriverInfo(driver));
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
	public ResponseEntity<?> setStatusParkingLotAsBooked(Authentication authentication, @Valid @RequestBody ParkingLot parkingLot) throws IOException {
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
		else if(!parkingLotTicketRepository.findByUsername(authentication.getName()).isEmpty()) {
			return new ResponseEntity<String>("You already have bought a ticket. You can book a new parking lot when you will free the current one.", HttpStatus.CONFLICT);
		}
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

	@PutMapping(path = DRIVER_STATUS_PARKINGLOT_SET_STATUS_DISABLED, consumes = "application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity setStatusParkingLotAsDisabled(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		if (parkingLot.getStatus() != Status.BOOKED && parkingLot.getStatus() != Status.OCCUPIED) {
			List<ParkingLot> parks = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(),
					parkingLot.getNumberOfParkingLot());
			ParkingLot park = parks.get(0);
			park.setStatus(Status.DISABLED);
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
		 
		
		
	@GetMapping(path = DRIVER_GET_NEAREST_PARKING_LOT)
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<ParkingLot> getNearestParkingLot(Authentication authentication, @NotNull @RequestParam double latitude, @NotNull @RequestParam double longitude) {
		DriverInfo driverInfo = driverRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Driver info Not Found with username: " + authentication.getName()));
		List<ParkingLot> compatibleParkingLots = parkingLotRepository.findCompatibleFreeParkingLots(driverInfo.getHandicap(), driverInfo.getVehicleType());
		if(compatibleParkingLots.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			double min = 10000000.0;
			int index = 0;
			for(int i = 0; i < compatibleParkingLots.size(); i++) {
				double parkLat = Double.parseDouble(compatibleParkingLots.get(i).getCoordinates().getLatitude());
				double parkLng = Double.parseDouble(compatibleParkingLots.get(i).getCoordinates().getLongitude());
				double distance = Math.hypot(Math.abs(latitude - parkLat), Math.abs(longitude - parkLng));
				if(distance < min) {
					min = distance;
					index = i;
				}
			}
			return ResponseEntity.ok(compatibleParkingLots.get(index));
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
			ParkingLotBooking parkingLotBooking= parkingLotBookings.get(0);

			parkingLotBookingRepository.delete(parkingLotBooking);
			parkingLotRepository.save(parkingLot);
			parkingLotTicketRepository.save(parkingLotTicket);
			return new ResponseEntity(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
}
