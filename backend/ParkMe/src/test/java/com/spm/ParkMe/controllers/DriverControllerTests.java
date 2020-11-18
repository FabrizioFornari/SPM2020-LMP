package com.spm.ParkMe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.UserRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class DriverControllerTests {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	DriverInfoRepository driverInfoRepository;
	
	@InjectMocks
    private DriverController driverController;
	
	@Autowired
	PasswordEncoder encoder;
	@Autowired
    private WebApplicationContext context;
	
	private User testDriver;
	private DriverInfo testDriverInfo;
	private MockMvc mockMvc;
	private JacksonTester<Driver> jsonDriver;
	private JacksonTester<WrongObject> jsonWrongObject;
	
	
		
	
	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
		driverInfoRepository.deleteAll();
		userRepository.save(DRIVER_OBJECT);
		
		mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
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
	
	@Test
	public void handicapPermitsRequestUnauthorizedWithoutToken() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void handicapPermitsRequestwithTokenAndCanRequestHandicapPermits() throws Exception {

		driverInfoRepository.save(new DriverInfo(DRIVER_OBJECT));
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void handicapPermitsRequestWithAlreadyHandicapReturnsConflict() throws Exception {
		DriverInfo infoWithHandicap = new DriverInfo(DRIVER_OBJECT);
		infoWithHandicap.setHandicap(true);
		driverInfoRepository.save(infoWithHandicap);
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isAlreadyReported());
	}
	
}
