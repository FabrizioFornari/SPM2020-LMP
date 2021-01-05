package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.EndpointContants.PARKING_MANAGER_ENDPOINT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.repositories.ParkingLotRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;

@RestController
@RequestMapping(PARKING_MANAGER_ENDPOINT)
@CrossOrigin(origins = "*", maxAge=3600)
public class VigilantController {

	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	
	public VigilantController() {
		// TODO Auto-generated constructor stub
	}

}
