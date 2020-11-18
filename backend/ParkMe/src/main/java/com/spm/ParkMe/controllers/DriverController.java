package com.spm.ParkMe.controllers;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.UserRepository;
import static com.spm.ParkMe.constants.EndpointContants.*;


@RestController
@RequestMapping(DRIVER_ENDPOINT)
public class DriverController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DriverInfoRepository driverRepository;
	
	@Autowired 
	private HandicapPermitsRequestsRepository handicapRepository;

	@PostMapping(path=DRIVER_REGISTRATION_ENDPOINT,consumes = "application/json" )
	public void registration(@Valid @RequestBody Driver driver) throws IOException {
		repository.save(driver);
		driverRepository.save(new DriverInfo(driver));
	}
	
	@PostMapping(path=DRIVER_HANDICAP_PERMITS_ENDPOINT, consumes="application/json")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity uploadHandicapPermitsRequest(Authentication authentication)throws IOException {
		String username = authentication.getName();
		DriverInfo driverInfo = driverRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user is not registered in DriverInfo collection."));
		//check if he already has handicap set
		if(driverInfo.getHandicap()) {
			return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
		}
		//check if the user has already sent a request
		List<HandicapPermitsRequest> userPendingRequests =  handicapRepository.findAll().stream().filter(req -> !req.isProcessed() && req.getUsername().equals(username)).collect(Collectors.toList());
		if(userPendingRequests.size() > 0) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		handicapRepository.save(new HandicapPermitsRequest(username, System.currentTimeMillis(), false, false));
		return new ResponseEntity(HttpStatus.OK); 
	}
	
}
	

