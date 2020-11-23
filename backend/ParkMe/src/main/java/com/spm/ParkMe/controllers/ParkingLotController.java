package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.EndpointContants.PARKINGLOT_ENDPOINT;
import static com.spm.ParkMe.constants.EndpointContants.PARKINGLOT_CREATE_ENDPOINT;
import static com.spm.ParkMe.constants.EndpointContants.PARKINGLOT_DELETE_ENDPOINT;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@RestController
@RequestMapping(PARKINGLOT_ENDPOINT)
@CrossOrigin(origins = "*", maxAge=3600)

public class ParkingLotController {

	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@PostMapping(path=PARKINGLOT_CREATE_ENDPOINT,consumes = "application/json" )
	public void createParkingLot(@Valid @RequestBody ParkingLot parkingLot) throws IOException {
		parkingLotRepository.save(parkingLot);
	}
	
	@DeleteMapping(path=PARKINGLOT_DELETE_ENDPOINT,consumes = "application/json" )
	public void deleteParkingLot(@Valid @RequestBody ParkingLot parkingLot)throws IOException {
		parkingLotRepository.delete(parkingLot);
	}
}
