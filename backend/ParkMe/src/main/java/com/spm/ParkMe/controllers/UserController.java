package com.spm.ParkMe.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.encryption.Encryptor;
import com.spm.ParkMe.models.Credentials;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@PostMapping(path="/api/login", consumes = "application/json")
	public User login(@RequestBody Credentials credentials) {
		User user = repository.findByUsername(credentials.getUsername()).get();
		if(user != null) {
			String encrypted = Encryptor.encryptPassword(credentials.getPassword());
			if(encrypted.equals(user.getPassword())) {
				return user;
			}
		} 
		return null;
	}
}
