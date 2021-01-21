package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.EndpointContants.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.spm.ParkMe.enums.PersonalParkingLotStatus;
import com.spm.ParkMe.models.Coordinates;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.Parking;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.PersonalParkingLot;
import com.spm.ParkMe.models.PersonalParkingLotSubscription;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.ChangeParkingLot;
import com.spm.ParkMe.models.requestBody.ChangePersonalParkingLot;
import com.spm.ParkMe.models.requestBody.TotalParkingLots;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotSubscriptionRepository;

@RestController
@RequestMapping(PARKING_MANAGER_ENDPOINT)
@CrossOrigin(origins = "*", maxAge=3600)

public class ParkingManagerController {

	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private PersonalParkingLotRepository personalParkingLotRepository;
	
	@Autowired
	private PersonalParkingLotSubscriptionRepository personalParkingLotSubscriptionRepository;
	
	
	@PostMapping(path=PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> createParkingLot(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		List<ParkingLot> parkingLotsWithSameNumber = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(), parkingLot.getNumberOfParkingLot());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			return new ResponseEntity<ParkingLot>(HttpStatus.CONFLICT);
		}
		parkingLotRepository.save(parkingLot);
		return new ResponseEntity<ParkingLot>(HttpStatus.OK);
	}
	
	@PutMapping(path=PARKING_MANAGER_UPDATE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> updateParkingLot(@Valid @RequestBody ChangeParkingLot changeParkingLot) throws IOException {
		List<ParkingLot> parkingLotsWithSameNumber = parkingLotRepository.findByStreetAndNumberOfParkingLot(changeParkingLot.getOldStreet(), changeParkingLot.getOldNumberOfParkingLot());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			ParkingLot parkingLotToChange = parkingLotsWithSameNumber.get(0);
			if(changeParkingLot.getNewStreet() != null && changeParkingLot.getNewStreet() != "") {
				parkingLotToChange.setStreet(changeParkingLot.getNewStreet());
			}
			if(changeParkingLot.getNewStreet() != null && changeParkingLot.getNewStreet() != "") {
				parkingLotToChange.setStreet(changeParkingLot.getNewStreet());
			}
			if(changeParkingLot.getNewNumberOfParkingLot() != null) {
				parkingLotToChange.setNumberOfParkingLot(changeParkingLot.getNewNumberOfParkingLot());
			}
			if(changeParkingLot.isNewIsHandicapParkingLot() != null) {
				parkingLotToChange.setIsHandicapParkingLot(changeParkingLot.isNewIsHandicapParkingLot());
			}
			if(changeParkingLot.getNewPricePerHours() != null) {
				parkingLotToChange.setPricePerHour(changeParkingLot.getNewPricePerHours());
			}
			if(changeParkingLot.getNewTypeOfVehicle() != null && changeParkingLot.getNewTypeOfVehicle() != "") {
				parkingLotToChange.setTypeOfVehicle(changeParkingLot.getNewTypeOfVehicle());
			}
			String newLatitude = parkingLotToChange.getCoordinates().getLatitude();
			String newLongitude = parkingLotToChange.getCoordinates().getLongitude();
			if(changeParkingLot.getNewLatitude() != null && changeParkingLot.getNewLatitude() != "") {
				newLatitude = changeParkingLot.getNewLatitude() ;
			}
			if(changeParkingLot.getNewLongitude() != null && changeParkingLot.getNewLongitude() != "") {
				newLongitude = changeParkingLot.getNewLongitude() ;
			}
			parkingLotToChange.setCoordinates(new Coordinates(newLatitude, newLongitude));
			parkingLotRepository.save(parkingLotToChange);
			return new ResponseEntity<ParkingLot>(HttpStatus.OK);
		}
		return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path=PARKING_MANAGER_DELETE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> deleteParkingLot(@NotNull @RequestParam String street , @NotNull @RequestParam Integer numberOfParkingLot)throws IOException {
		List<ParkingLot> parkingLotsWithSameNumber = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		if(!parkingLotsWithSameNumber.isEmpty()) {
			parkingLotRepository.delete(parkingLotsWithSameNumber.get(0));
			return new ResponseEntity<ParkingLot>(HttpStatus.OK);
		}
		return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path=PARKING_MANAGER_GET_ALL_PARKINGLOT_ENDPOINT, consumes = "application/json")
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public  ResponseEntity<TotalParkingLots> allParkingLots()  {
		return new ResponseEntity<TotalParkingLots>(new TotalParkingLots(parkingLotRepository.findAll(), personalParkingLotRepository.findAll()),HttpStatus.OK);
	}
	
	@GetMapping(path=PARKING_MANAER_GET_ALL_PARKINGLOTS_STREET_ENDPOINT, consumes = "application/json")
	@PreAuthorize("hasRole('PARKING_MANAGER') or hasRole('DRIVER')")
	public  List<ParkingLot> getParkingLotsForSpecificStreet(@NotNull @RequestParam String street)  {
		return parkingLotRepository.findByStreet(street);
	}
	
	@PostMapping(path=PARKING_MANAGER_CREATE_PERSONAL_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> createPersonalParkingLot(@Valid @RequestBody PersonalParkingLot parkingLot) throws IOException {
		List<PersonalParkingLot> parkingLotsWithSameNumber = personalParkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(), parkingLot.getNumberOfParkingLot());
		System.out.println(parkingLotsWithSameNumber.size());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			return new ResponseEntity<PersonalParkingLot>(HttpStatus.CONFLICT);
		}
		parkingLot.setPersonalParkingLotStatus(PersonalParkingLotStatus.FREE);
		personalParkingLotRepository.save(parkingLot);
		return new ResponseEntity<PersonalParkingLot>(HttpStatus.OK);
	}
	
	@PutMapping(path=PARKING_MANAGER_UPDATE_PERSONAL_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> updatePersonalParkingLot(@Valid @RequestBody ChangePersonalParkingLot changeParkingLot) throws IOException {
		List<PersonalParkingLotSubscription> subscriptions = personalParkingLotSubscriptionRepository.findByStreetAndNumberOfParkingLot(changeParkingLot.getOldStreet(), changeParkingLot.getOldNumberOfParkingLot());
		if(!subscriptions.isEmpty()) {
			return new ResponseEntity(new MessageResponse("The parking lot has an active subscription. Please contact the administrator."), HttpStatus.CONFLICT);
		}
		List<PersonalParkingLot> parkingLotsWithSameNumber = personalParkingLotRepository.findByStreetAndNumberOfParkingLot(changeParkingLot.getOldStreet(), changeParkingLot.getOldNumberOfParkingLot());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			PersonalParkingLot parkingLotToChange = parkingLotsWithSameNumber.get(0);
			if(changeParkingLot.getNewStreet() != null && changeParkingLot.getNewStreet() != "") {
				parkingLotToChange.setStreet(changeParkingLot.getNewStreet());
			}
			if(changeParkingLot.getNewStreet() != null && changeParkingLot.getNewStreet() != "") {
				parkingLotToChange.setStreet(changeParkingLot.getNewStreet());
			}
			if(changeParkingLot.getNewNumberOfParkingLot() != null) {
				parkingLotToChange.setNumberOfParkingLot(changeParkingLot.getNewNumberOfParkingLot());
			}
			if(changeParkingLot.getNewIsHandicapParkingLot() != null) {
				parkingLotToChange.setIsHandicapParkingLot(changeParkingLot.getNewIsHandicapParkingLot());
			}
			if(changeParkingLot.getNewPrice() != null) {
				parkingLotToChange.setPrice(changeParkingLot.getNewPrice());
			}
			if(changeParkingLot.getNewTypeOfVehicle() != null && changeParkingLot.getNewTypeOfVehicle() != "") {
				parkingLotToChange.setTypeOfVehicle(changeParkingLot.getNewTypeOfVehicle());
			}
			String newLatitude = parkingLotToChange.getCoordinates().getLatitude();
			String newLongitude = parkingLotToChange.getCoordinates().getLongitude();
			if(changeParkingLot.getNewLatitude() != null && changeParkingLot.getNewLatitude() != "") {
				newLatitude = changeParkingLot.getNewLatitude() ;
			}
			if(changeParkingLot.getNewLongitude() != null && changeParkingLot.getNewLongitude() != "") {
				newLongitude = changeParkingLot.getNewLongitude() ;
			}
			parkingLotToChange.setCoordinates(new Coordinates(newLatitude, newLongitude));
			personalParkingLotRepository.save(parkingLotToChange);
			return new ResponseEntity<ParkingLot>(HttpStatus.OK);
		}
		return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path=PARKING_MANAGER_DELETE_PERSONAL_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> deletePersonalParkingLot(@NotNull @RequestParam String street , @NotNull @RequestParam Integer numberOfParkingLot)throws IOException {
		List<PersonalParkingLot> parkingLotsWithSameNumber = personalParkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		if(!parkingLotsWithSameNumber.isEmpty()) {
			personalParkingLotRepository.delete(parkingLotsWithSameNumber.get(0));
			return new ResponseEntity<ParkingLot>(HttpStatus.OK);
		}
		return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
	}

}
