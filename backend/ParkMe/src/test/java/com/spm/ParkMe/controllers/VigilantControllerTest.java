package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.ParkingLotCostants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class VigilantControllerTest {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
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
	@WithMockUser(roles= {"VIGILANT"})
	public void setParkingLotStatusDisabledReturnsOK() throws Exception {
		parkingLotRepository.save(PARKINGLOT_OBJECT);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				VIGILANT_ENDPOINT + VIGILANT_SET_PARKINGLOT_STATUS_DISABLED).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(PARKINGLOT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(Status.DISABLED, parkingLotRepository.findAll().get(0).getStatus());
	}
	
	@Test
	@WithMockUser(roles= {"VIGILANT"})
	public void setParkingLotStatusEnabledReturnsOK() throws Exception {
		parkingLotRepository.save(PARKINGLOT_OBJECT);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				VIGILANT_ENDPOINT + VIGILANT_SET_PARKINGLOT_STATUS_ENABLED).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(PARKINGLOT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(Status.FREE, parkingLotRepository.findAll().get(0).getStatus());
	}

}
