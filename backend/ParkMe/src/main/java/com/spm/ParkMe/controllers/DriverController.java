package com.spm.ParkMe.controllers;


import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
	public void registration(@Valid @RequestBody Driver driver) throws IOException {
		repository.save(driver);
	}
}
	

