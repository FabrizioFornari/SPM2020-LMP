package com.spm.ParkMe.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PostMapping("/login")
	public String login() {
		return "You successfully logged in!";
	}
}
