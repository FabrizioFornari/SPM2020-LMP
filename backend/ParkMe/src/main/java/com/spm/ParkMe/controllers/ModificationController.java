package com.spm.ParkMe.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.ChangeMailInfo;
import com.spm.ParkMe.models.requestBody.ChangePasswordInfo;
import com.spm.ParkMe.models.requestBody.ChangePhoneInfo;
import com.spm.ParkMe.models.requestBody.ChangePlateVehicleTypeInfo;
import com.spm.ParkMe.models.requestBody.Credentials;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.UserRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(MODIFICATION_ENDPOINT)
public class ModificationController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DriverInfoRepository driverInfoRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping(EMAIL_MODIFICATION_ENDPOINT)
	public ResponseEntity<User> modifyEmail(Authentication authentication, @Valid @RequestBody ChangeMailInfo mailInfo) {
		String authenticatedUsername = authentication.getName();
		if(authenticatedUsername.equals(mailInfo.getCurrentEmail())) {
			User user = repository.findByUsername(mailInfo.getCurrentEmail()).orElseThrow(() -> new UsernameNotFoundException("The provided current email is wrong."));
			user.setUsername(mailInfo.getNewEmail());
			user.setEmail(mailInfo.getNewEmail());
			repository.save(user);
			if(user.getRole() == Roles.ROLE_DRIVER) {
				DriverInfo driverInfo = driverInfoRepository.findByUsername(mailInfo.getCurrentEmail()).orElseThrow(() -> new UsernameNotFoundException("The provided current email is not registrated in Driver Info collection."));
				driverInfo.setUsername(mailInfo.getNewEmail());
				driverInfoRepository.save(driverInfo);
			}
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping(PHONE_MODIFICATION_ENDPOINT)
	public ResponseEntity<User> modifyPhone(Authentication authentication, @Valid @RequestBody ChangePhoneInfo phoneInfo ){
		String authenticatedUsername = authentication.getName();
		User user = repository.findByUsername(authenticatedUsername).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		user.setPhone(phoneInfo.getNewPhone());
		repository.save(user);
	return ResponseEntity.ok(user);
	}
	
	@PostMapping(PLATE_VEHICLE_MODIFICATION_ENDPOINT)
	public ResponseEntity<User> modifyPlateAndVehicleType(Authentication authentication, @Valid @RequestBody ChangePlateVehicleTypeInfo plateVehicleTypeInfo ){
		String authenticatedUsername = authentication.getName();
		User user = repository.findByUsername(authenticatedUsername).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		if(user.getRole() == Roles.ROLE_DRIVER) {
			DriverInfo driverInfo = driverInfoRepository.findByUsername(authenticatedUsername).orElseThrow(() -> new UsernameNotFoundException("The provided current email is not registrated in Driver Info collection."));
			driverInfo.setPlate(plateVehicleTypeInfo.getNewPlate());
			driverInfo.setVehicleType(plateVehicleTypeInfo.getNewVehicleType());
			driverInfoRepository.save(driverInfo);
			return ResponseEntity.ok(user);
		}else {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
		
		
	
	}
	


	@PostMapping(PASSWORD_MODIFICATION_ENDPOINT)
	public ResponseEntity<?> modifyPassword(Authentication authentication, @Valid @RequestBody ChangePasswordInfo passwordInfo){
		String authenticatedUsername = authentication.getName();
		User user = repository.findByUsername(authenticatedUsername).orElseThrow(() -> new UsernameNotFoundException("Cannot find user with token's username."));
		if(encoder.matches(passwordInfo.getCurrentPassword(), user.getPassword())) {
			user.setPassword(encoder.encode(passwordInfo.getNewPassword()));
			repository.save(user);
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
	}
}
