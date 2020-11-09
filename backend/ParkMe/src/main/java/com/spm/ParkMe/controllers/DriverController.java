package com.spm.ParkMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.repositories.DriverRepository;



@RestController
public class DriverController {

	@Autowired
	private DriverRepository repository;
	@PostMapping(path="api/registration",consumes = "application/json" )
	public Driver registration(@RequestBody Driver driver) {
		repository.save(driver);
		 System.out.println(driver);
		 return driver;
	}
	
	
	
}
