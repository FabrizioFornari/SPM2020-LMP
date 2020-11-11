package com.spm.ParkMe.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.repositories.ParkingManagerRepository;
import com.spm.ParkMe.repositories.VigilantRepository;

@CrossOrigin(origins ="*", maxAge =3600)
@RestController

public class AdminController {

	@Autowired
	private ParkingManagerRepository repository;
	@Autowired
	private VigilantRepository vigilant_repository;
	
	@PostMapping("/api/parkingmanager/registration")
	@PreAuthorize("hasRole('ADMIN')")
	public BodyBuilder parkingManagerRegistration(@RequestBody ParkingManager pmanager)  {
		if(pmanager.isValid() == true) {
			repository.save(pmanager);
			
			 return  ResponseEntity.ok();		
		}else {
			
			 return  ResponseEntity.badRequest();
		}
		
	}
	
	@PostMapping("/api/vigilant/registration")
	@PreAuthorize("hasRole('ADMIN')")
	public BodyBuilder vigilantRegistration(@RequestBody Vigilant vigilant)  {
		if(vigilant.isValid() == true) {
			vigilant_repository.save(vigilant);
			System.out.println(vigilant);
			 return  ResponseEntity.ok();		
		}else {
		
			 return   ResponseEntity.badRequest();
		}
		
	}
	
	
}
