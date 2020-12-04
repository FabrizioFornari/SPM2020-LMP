package com.spm.ParkMe.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.StreetInfo;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
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
		else {
			//set parking lot status as booked
			List<ParkingLot> parks = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(),
					parkingLot.getNumberOfParkingLot());
			ParkingLot park = parks.get(0);
			park.setStatus(Status.BOOKED);
			parkingLotRepository.save(park);
			//create a booking object
			ParkingLotBooking booking = new ParkingLotBooking(parkingLot.getStreet(), parkingLot.getNumberOfParkingLot(), authentication.getName(), System.currentTimeMillis());
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

}
