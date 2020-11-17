package com.spm.ParkMe.controllers;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.UserRepository;
import static com.spm.ParkMe.constants.EndpointContants.*;


@RestController
@RequestMapping(DRIVER_ENDPOINT)
public class DriverController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DriverInfoRepository driverRepository;
	
	@PostMapping(path=DRIVER_REGISTRATION_ENDPOINT,consumes = "application/json" )
	public void registration(@Valid @RequestBody Driver driver) throws IOException {
		repository.save(driver);
		driverRepository.save(new DriverInfo(driver));
	}
}
	

