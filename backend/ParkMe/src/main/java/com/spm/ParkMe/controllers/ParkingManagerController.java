package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.EndpointContants.PARKING_MANAGER_ENDPOINT;
import static com.spm.ParkMe.constants.EndpointContants.ADMIN_GET_ALL_HANDICAP_PERMITS_ENDPOINT;
import static com.spm.ParkMe.constants.EndpointContants.PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT;
import static com.spm.ParkMe.constants.EndpointContants.PARKING_MANAGER_DELETE_PARKINGLOT_ENDPOINT;
import static com.spm.ParkMe.constants.EndpointContants.PARKING_MANAGER_GET_ALL_PARKINGLOT_ENDPOINT;


import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@RestController
@RequestMapping(PARKING_MANAGER_ENDPOINT)
@CrossOrigin(origins = "*", maxAge=3600)

public class ParkingManagerController {

	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@PostMapping(path=PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	public void createParkingLot(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		parkingLotRepository.save(parkingLot);
	}
	
	@DeleteMapping(path=PARKING_MANAGER_DELETE_PARKINGLOT_ENDPOINT,consumes = "application/json" )
	public void deleteParkingLot(@Valid @RequestBody ParkingLot parkingLot)throws IOException {
		parkingLotRepository.delete(parkingLot);
	}
	
	@GetMapping(path=PARKING_MANAGER_GET_ALL_PARKINGLOT_ENDPOINT)
	@PreAuthorize("hasRole('PARKING_MANAGER')")
	public  List<ParkingLot> allParkingLots()  {
		return parkingLotRepository.findAll();
	}
	
	
}
