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
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.UserRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;
import static com.spm.ParkMe.constants.ParkingLotCostants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class DriverControllerTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DriverInfoRepository driverInfoRepository;
	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	@Autowired
	ParkingLotBookingRepository parkingLotBookingRepository;
	
	@Autowired
	HandicapPermitsRequestsRepository handicapRequestsRepository;
	
	
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
	private JacksonTester<ParkingLot> jsonParkingLot;
	private ParkingLot parkingLot;
	
		
	
	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
		driverInfoRepository.deleteAll();
		handicapRequestsRepository.deleteAll();
		parkingLotRepository.deleteAll();
		userRepository.save(DRIVER_OBJECT);
		parkingLot= new ParkingLot(VALID_STREET, VALID_NUMBROFPARKINGLOT, VALID_ISHANDICAPPARKINGLOT, VALID_PRICEPERHOURS, CAR, VALID_COORDINATES);
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
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
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
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void handicapPermitsRequestWithAlreadyHandicapReturnsIsAlreadyReported() throws Exception {
		
		DriverInfo infoWithHandicap = new DriverInfo(DRIVER_OBJECT);
		infoWithHandicap.setHandicap(true);
		driverInfoRepository.save(infoWithHandicap);
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isAlreadyReported());
	}
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void handicapPermitsRequestAlreadyExistingReturnsConflict() throws Exception {
		
		driverInfoRepository.save(new DriverInfo(DRIVER_OBJECT));
		handicapRequestsRepository.save(new HandicapPermitsRequest(DRIVER_MAIL, 123456789, false, false));
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				DRIVER_ENDPOINT + DRIVER_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void setStatusParkingLotAsBookedWhenStatusEqualToFree() throws Exception {
		
		driverInfoRepository.save(new DriverInfo(DRIVER_OBJECT));
		
		parkingLotRepository.save(parkingLot);
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				DRIVER_ENDPOINT + DRIVER_STATUS_PARKINGLOT_SET_STATUS_BOOKED).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(parkingLot).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void setStatusParkingLotAsFreeWhenStatusDifferentFromFree() throws Exception {
		parkingLotRepository.save(parkingLot);
		parkingLot.setStatus(Status.BOOKED);
		System.out.println(parkingLot.getStatus());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				DRIVER_ENDPOINT + DRIVER_STATUS_PARKINGLOT_SET_STATUS_FREE).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(parkingLot).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());	
		
	}
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void setStatusParkingLotAsOccupiedWhenStatusEqualToBooked() throws Exception {
		parkingLotRepository.save(parkingLot);
		parkingLot.setStatus(Status.BOOKED);
		System.out.println(parkingLot.getStatus());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				DRIVER_ENDPOINT + DRIVER_STATUS_PARKINGLOT_SET_STATUS_OCCUPIED).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(parkingLot).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());	
		
	}
	
	@Test
	@WithMockUser(username = DRIVER_MAIL, roles= {"DRIVER"})
	public void setStatusParkingLotAsDisabledWhenStatusDifferentFromOccupiedAndBooked() throws Exception {
		parkingLotRepository.save(parkingLot);
		System.out.println(parkingLot.getStatus());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				DRIVER_ENDPOINT + DRIVER_STATUS_PARKINGLOT_SET_STATUS_DISABLED).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingLot.write(parkingLot).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());	
		
	}
}
