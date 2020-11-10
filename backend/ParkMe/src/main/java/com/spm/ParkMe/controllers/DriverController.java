package com.spm.ParkMe.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.repositories.DriverRepository;



@RestController
public class DriverController {

	
	
	
	@Autowired
	private DriverRepository repository;
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	

	@PostMapping(path="api/registration",consumes = "application/json" )
	public ResponseEntity<String> driverRegistration(@RequestBody Driver driver)  {
	if(driver.isValid() == true) {
		repository.save(driver);
		System.out.println(driver);
		 return new ResponseEntity<String>("Driver created successfully",  HttpStatus.CREATED);
		
	
	}else {
		 return new ResponseEntity<String>("Try again something went wrong", HttpStatus.BAD_REQUEST);
	}
	
	}
	
}
	

