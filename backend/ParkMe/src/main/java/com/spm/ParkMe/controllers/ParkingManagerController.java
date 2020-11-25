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

import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.ChangeParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@RestController
@RequestMapping(PARKING_MANAGER_ENDPOINT)
@CrossOrigin(origins = "*", maxAge=3600)

public class ParkingManagerController {

	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@PostMapping(path=PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> createParkingLot(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		//first get the parking lots for the street
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreet(parkingLot.getStreet());
		//check if there is already a parking lot with specified number
		List<ParkingLot> parkingLotsWithSameNumber = parkingLots.stream().filter(lot -> lot.getNumberOfParkingLot() == parkingLot.getNumberOfParkingLot()).collect(Collectors.toList());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			return new ResponseEntity<ParkingLot>(HttpStatus.CONFLICT);
		}
		parkingLotRepository.save(parkingLot);
		return new ResponseEntity<ParkingLot>(HttpStatus.OK);
	}
	
	@PutMapping(path=PARKING_MANAGER_UPDATE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> updateParkingLot(@Valid @RequestBody ChangeParkingLot changeParkingLot) throws IOException {
		//first get the parking lots for the street
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreet(changeParkingLot.getStreet());
		//check if there is already a parking lot with specified number
		List<ParkingLot> parkingLotsWithSameNumber = parkingLots.stream().filter(lot -> lot.getNumberOfParkingLot() == changeParkingLot.getNumberOfParkingLot()).collect(Collectors.toList());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			ParkingLot parkingLotToChange = parkingLotsWithSameNumber.get(0);
			parkingLotRepository.delete(parkingLotToChange);
			parkingLotRepository.save(changeParkingLot.getParkinglot());
			return new ResponseEntity<ParkingLot>(HttpStatus.OK);
		}
		return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path=PARKING_MANAGER_DELETE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public ResponseEntity<?> deleteParkingLot(@NotNull @RequestParam String street , @NotNull @RequestParam Integer numberOfParkingLot)throws IOException {
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreet(street);
		List<ParkingLot> parkingLotsWithSameNumber = parkingLots.stream().filter(lot -> lot.getNumberOfParkingLot() == numberOfParkingLot).collect(Collectors.toList());
		if(!parkingLotsWithSameNumber.isEmpty()) {
			parkingLotRepository.delete(parkingLotsWithSameNumber.get(0));
			return new ResponseEntity<ParkingLot>(HttpStatus.OK);
		}
		return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path=PARKING_MANAGER_GET_ALL_PARKINGLOT_ENDPOINT, consumes = "application/json")
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public  List<ParkingLot> allParkingLots()  {
		return parkingLotRepository.findAll();
	}
	
	@GetMapping(path=PARKING_MANAER_GET_ALL_PARKINGLOTS_STREET_ENDPOINT, consumes = "application/json")
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public  List<ParkingLot> getParkingLotsForSpecificStreet(@Valid @RequestBody ParkingLot parkingLot)  {
		String street= parkingLot.getStreet();
		return parkingLotRepository.findByStreet(street);

	}
	
	

}
