package com.spm.ParkMe.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.UserRepository;


import static com.spm.ParkMe.constants.EndpointContants.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="*", maxAge =3600)
@RestController
@RequestMapping(ADMIN_ENDPOINT)
public class AdminController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private HandicapPermitsRequestsRepository handicapRepository;;
	
	@Autowired
	private PasswordEncoder encoder;
		
	@PostMapping(PARKING_MANAGER_REGISTRATION_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public void parkingManagerRegistration(@Valid @RequestBody ParkingManager pmanager)  {
		pmanager.setUsername(pmanager.getEmail());
		pmanager.setPassword(encoder.encode(pmanager.getPassword()));
		pmanager.setRole(Roles.ROLE_PARKING_MANAGER);
		repository.save(pmanager);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(VIGILANT_REGISTRATION_ENDPOINT)
	public void vigilantRegistration(@Valid @RequestBody Vigilant vigilant)  {
		vigilant.setUsername(vigilant.getEmail());
		vigilant.setPassword(encoder.encode(vigilant.getPassword()));
		vigilant.setRole(Roles.ROLE_VIGILANT);
		repository.save(vigilant);
	}
	
	@GetMapping(ADMIN_GET_HANDICAP_PERMITS_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public  List<HandicapPermitsRequest> allHandicapPermits()  {
		return handicapRepository.findAll();
	}
	
	@PostMapping(ADMIN_SET_HANDICAP_PERMITS_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> setHandicapPermits(@Valid @RequestBody String username, boolean isAccepted ) {
		HandicapPermitsRequest handicapPermits= handicapRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Cannot find Username"));;
		handicapPermits.setProcessed(true);
		handicapPermits.setAccepted(isAccepted);
		return ResponseEntity.ok(handicapPermits);
	}
	
	
}
