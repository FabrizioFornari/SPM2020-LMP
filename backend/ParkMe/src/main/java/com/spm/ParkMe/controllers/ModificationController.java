package com.spm.ParkMe.controllers;

import java.security.Principal;

import javax.validation.Valid;

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

import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.requestBody.ChangeMailInfo;
import com.spm.ParkMe.models.requestBody.Credentials;
import com.spm.ParkMe.repositories.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modify")
public class ModificationController {
	
	@Autowired
	private UserRepository repository;
	
	@PostMapping("/email")
	public ResponseEntity<User> modifyEmail(Authentication authentication, @Valid @RequestBody ChangeMailInfo mailInfo) {
		String authenticatedUsername = authentication.getName();
		if(authenticatedUsername.equals(mailInfo.getCurrentEmail())) {
			User user = repository.findByUsername(mailInfo.getCurrentEmail()).orElseThrow(() -> new UsernameNotFoundException("The provided current email is wrong."));
			//delete user from db
			repository.delete(user);
			user.setUsername(mailInfo.getNewEmail());
			user.setEmail(mailInfo.getNewEmail());
			repository.save(user);
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
	}
}
