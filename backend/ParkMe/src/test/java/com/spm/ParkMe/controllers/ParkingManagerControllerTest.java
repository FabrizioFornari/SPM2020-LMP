package com.spm.ParkMe.controllers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.io.IOException;

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
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.UserRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.ParkingLotCostants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.DRIVER_OBJECT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class ParkingManagerControllerTest {

	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	@InjectMocks
    private ParkingManagerController parkingLotController;
	
	@Autowired
    private WebApplicationContext context;
	
	private ParkingLot testParkingLot;
	
	private MockMvc mockMvc;
	private JacksonTester<ParkingLot> jsonParkingLot;
	
	
	@BeforeEach
	public void setUp() {
		parkingLotRepository.deleteAll();
	
		//parkingLotRepository.save(PARKINGLOT_OBJECT);
		
		mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
		JacksonTester.initFields(this, new ObjectMapper()); 
		 
	}
	
	@Test
	@WithMockUser(username = PARKING_MANAGER_MAIL, roles= {"PARKING_MANAGER"})
	public void createdParkingLotReturnsOK() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				PARKING_MANAGER_ENDPOINT + PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(PARKINGLOT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test 
	@WithMockUser(username = PARKING_MANAGER_MAIL, roles= {"PARKING_MANAGER"})
	void createdParkingLotReturnsConflictWhenWrongNumber() throws Exception {
		parkingLotRepository.save(PARKINGLOT_OBJECT);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				PARKING_MANAGER_ENDPOINT + PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(PARKINGLOT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}
	
	@Test 
	@WithMockUser(username = PARKING_MANAGER_MAIL, roles= {"PARKING_MANAGER"})
	void deletedParkingLotReturnsConflictWhenWrongNumber() throws Exception {
		parkingLotRepository.save(PARKINGLOT_OBJECT);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				PARKING_MANAGER_ENDPOINT + PARKING_MANAGER_DELETE_PARKINGLOT_ENDPOINT).accept(
				MediaType.APPLICATION_JSON).param("street",VALID_STREET).param("numberOfParkingLot","3")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
