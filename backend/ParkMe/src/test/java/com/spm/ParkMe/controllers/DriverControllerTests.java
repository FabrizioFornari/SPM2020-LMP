package com.spm.ParkMe.controllers;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.repositories.DriverRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class DriverControllerTests {
	
	@Mock
	DriverRepository driverRepository;
	
	@InjectMocks
    private DriverController driverController;
	
	private Driver driver;
	private MockMvc mockMvc;
	private JacksonTester<Driver> jsonDriver;
	private WrongDriver wrongDriver;
	private JacksonTester<WrongDriver> jsonWrongDriver;
	
	//create wrong class for testing 
	public class WrongDriver {
		private String firstName;
		
		public WrongDriver() {
			
		}
		public WrongDriver(String name) {
			this.setFirstName(name);
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	}
	
	@BeforeEach
	public void setUp() {
		driver = new Driver("manuel", "cretone","RSSMRA80A01F205X",
							"prova@mail.com","+393384283440","ES943AB","dddfd","dddfd");
		mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .build();
		JacksonTester.initFields(this, new ObjectMapper()); 
		 
		//setUP wrongDriver
		wrongDriver = new WrongDriver("");
	}
	
	@Test
	public void registrationDriverReturnSaved() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/registration").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonDriver.write(driver).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void failedRegistrationDriverReturnSaved() throws Exception {
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/registration").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongDriver.write(wrongDriver).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
}
