package com.spm.ParkMe.controllers;

import java.util.List;
import static com.spm.ParkMe.constants.EndpointContants.*;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.spm.ParkMe.models.JwtResponse;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.Credentials;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.security.jwt.JwtUtils;
import com.spm.ParkMe.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DriverInfoRepository driverInfoRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping(LOGIN_ENDPOINT)
	public ResponseEntity<?> authenticateUser(@RequestBody Credentials credentials) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User user = userRepository.findByUsername(userDetails.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getEmail()));
		JwtResponse response = new JwtResponse(jwt, user);
		if(user.getRole() == Roles.ROLE_DRIVER) {
			DriverInfo driverInfo = driverInfoRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Driver Not Found with username: " + userDetails.getEmail()));
			response.setPlate(driverInfo.getPlate());
			response.setVehicleType(driverInfo.getVehicleType());
		}
		return ResponseEntity.ok(response);
	}

}
