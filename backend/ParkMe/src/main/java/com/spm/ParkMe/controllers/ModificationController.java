package com.spm.ParkMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository repository;
	

}
