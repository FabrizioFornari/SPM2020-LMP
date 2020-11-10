package com.spm.ParkMe.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.spm.ParkMe.models.Credentials;

@SpringBootTest
@WebMvcTest(value = AuthController.class)
public class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void loginDriverUser() throws Exception{
		Credentials driverCredentials = new Credentials("rocche@park.it", "Rocche");
		
		
	}

}
