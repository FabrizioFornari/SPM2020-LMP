package com.spm.ParkMe.controllers;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.repositories.UserRepository;



@RestController
public class DriverController {

	
	
	
	@Autowired
	private UserRepository repository;
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	

	@PostMapping(path="api/registration",consumes = "application/json" )
	public void registration(@Valid @RequestBody Driver driver,HttpServletResponse response) throws IOException {
		repository.save(driver);		
	}
	
}
	

