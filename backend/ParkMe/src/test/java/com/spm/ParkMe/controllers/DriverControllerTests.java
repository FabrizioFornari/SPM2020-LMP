package com.spm.ParkMe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.UserRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class DriverControllerTests {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	DriverInfoRepository driverInfoRepository;
	
	@InjectMocks
    private DriverController driverController;
	
	private MockMvc mockMvc;
	private JacksonTester<Driver> jsonDriver;
	private JacksonTester<WrongObject> jsonWrongObject;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .build();
		JacksonTester.initFields(this, new ObjectMapper()); 
	}
	
	@Test
	public void registeringCorrectDriverReturnsOK() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonDriver.write(DRIVER_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void registeringWrongDriverReturnsBadRequest() throws Exception {
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
}
