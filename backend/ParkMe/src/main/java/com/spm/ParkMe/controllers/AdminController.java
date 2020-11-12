package com.spm.ParkMe.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.repositories.UserRepository;

@CrossOrigin(origins ="*", maxAge =3600)
@RestController

public class AdminController {

	@Autowired
	private UserRepository repository;
		
	@PostMapping("/api/parkingmanager/registration")
	@PreAuthorize("hasRole('ADMIN')")
	public void parkingManagerRegistration(@Valid @RequestBody ParkingManager pmanager)  {
			repository.save(pmanager);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/vigilant/registration")
	public void vigilantRegistration(@Valid @RequestBody Vigilant vigilant)  {
		repository.save(vigilant);
	}
	
	
}
