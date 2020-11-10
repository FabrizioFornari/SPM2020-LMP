package com.spm.ParkMe.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.ParkingManager;

@CrossOrigin(origins ="*", maxAge =3600)
@RestController

public class AdminController {

	@PostMapping("/api/parkingmanager/registration")
	@PreAuthorize("hasRole('ADMIN')")
	public ParkingManager registrationParkingManager(ParkingManager pmanager,HttpServletResponse response) throws IOException {
		if(pmanager.isValid() == true) {
			//repository.save(pmanager);
			System.out.println(pmanager);
			return pmanager;
		
		}else {
		
			response.sendError(400,"richiesta sbagliata");
		}
		return null;
	}
}
