package com.spm.ParkMe.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Credentials;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;

@SpringBootTest
@WebMvcTest(value = AuthController.class)
public class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private Credentials driverCredentials = new Credentials("rocche@park.it", "Rocche");
	private Credentials vigilantCredentials = new Credentials("cret@park.it", "Cret");
	private Credentials parkingManagerCredentials = new Credentials("flash@park.it", "Flash");
	private Credentials adminCredentials = new Credentials("fusaro@turbomondialismo.it", "Fusaro");
	
	@BeforeAll
	public void setup() {
		userRepository.deleteAll();
		userRepository.save(new User(driverCredentials.getEmail(), driverCredentials.getEmail(),  encoder.encode(driverCredentials.getPassword()), Roles.ROLE_DRIVER));
		userRepository.save(new User(vigilantCredentials.getEmail(), vigilantCredentials.getEmail(),  encoder.encode(vigilantCredentials.getPassword()), Roles.ROLE_VIGILANT));
		userRepository.save(new User(parkingManagerCredentials.getEmail(), parkingManagerCredentials.getEmail(),  encoder.encode(parkingManagerCredentials.getPassword()), Roles.ROLE_PARKING_MANAGER));
		userRepository.save(new User(adminCredentials.getEmail(), adminCredentials.getEmail(),  encoder.encode(adminCredentials.getPassword()), Roles.ROLE_ADMIN));
	}
	
	@Test
	public void loginDriverUser() throws Exception{
		
	}

}
