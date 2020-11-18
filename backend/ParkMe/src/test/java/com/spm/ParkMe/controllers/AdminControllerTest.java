package com.spm.ParkMe.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.models.AdminHandicapRequestAcceptance;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class AdminControllerTest {

	@InjectMocks
    private AdminController adminController;
	
	@Autowired
	private HandicapPermitsRequestsRepository handicapPermitsRepository;
    @Autowired
    private WebApplicationContext context;
	
    @Autowired
	UserRepository userRepository;

	private MockMvc mockMvc;
	private List<HandicapPermitsRequest> handicapPermits =new ArrayList<HandicapPermitsRequest>();
	private JacksonTester<ParkingManager> jsonParkingManager;
	private JacksonTester<Vigilant> jsonVigilant;
	private JacksonTester<WrongObject> jsonWrongObject;
	private JacksonTester<List<HandicapPermitsRequest>> jsonHandicapPermits;
	private JacksonTester<HandicapPermitsRequest> jsonHandicapPermit;
	private JacksonTester <AdminHandicapRequestAcceptance> jsonAcceptance;
	
	@BeforeEach
	public void setUp() {
		handicapPermitsRepository.deleteAll();
		handicapPermits.add(new HandicapPermitsRequest(DRIVER_MAIL,444444444, false, false));
		handicapPermits.add(new HandicapPermitsRequest(VIGILANT_MAIL, 45444444, false, false));
		
		handicapPermitsRepository.saveAll(handicapPermits);
		mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
		JacksonTester.initFields(this, new ObjectMapper()); 
	}
	
	@Test
	public void registrationOfVigilantUnauthorizedWithoutToken() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonVigilant.write(VIGILANT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(roles= {"DRIVER"})
	public void registrationOfVigilantUnauthorizedWithWrongToken() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonVigilant.write(VIGILANT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles= {"ADMIN"})
	public void registrationOfVigilantAuthorizedWithRightToken() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonVigilant.write(VIGILANT_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(roles= {"ADMIN"})
	public void registrationOfWrongVigilantReturnsBadRequest() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void registrationOfParkingManagerUnauthorizedWithoutToken() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingManager.write(PARKING_MANAGER_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(roles= {"DRIVER"})
	public void registrationOfParkingManagerUnauthorizedWithWrongToken() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingManager.write(PARKING_MANAGER_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles= {"ADMIN"})
	public void registrationOfParkingManagerAuthorizedWithRightToken() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonParkingManager.write(PARKING_MANAGER_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(roles= {"ADMIN"})
	public void registrationOfWrongParkingManagerReturnsBadRequest() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + VIGILANT_REGISTRATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(WRONG_OBJECT).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	@WithMockUser(roles= {"ADMIN"})
	public void requestHandicapPermitsReturnsList() throws Exception {
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				ADMIN_ENDPOINT + ADMIN_GET_ALL_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonHandicapPermits.write(handicapPermits).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(2,jsonHandicapPermits.parse(response.getContentAsString()).getObject().size() );
	} 
	
	@Test
	@WithMockUser(roles = {"ADMIN"})
	public void requestSetHandicapPermits() throws Exception {
	
		AdminHandicapRequestAcceptance acceptance = new AdminHandicapRequestAcceptance(DRIVER_MAIL,true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				ADMIN_ENDPOINT + ADMIN_SET_HANDICAP_PERMITS_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonAcceptance.write(acceptance).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(true,jsonHandicapPermit.parse(response.getContentAsString()).getObject().isAccepted());
	} 
	
		
};
	