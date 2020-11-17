package com.spm.ParkMe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.repositories.UserRepository;

import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

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
    private WebApplicationContext context;
	
    @Autowired
	UserRepository userRepository;
	
	
	private MockMvc mockMvc;
	
	private JacksonTester<ParkingManager> jsonParkingManager;
	private JacksonTester<Vigilant> jsonVigilant;
	private JacksonTester<WrongObject> jsonWrongObject;
	
	@BeforeEach
	public void setUp() {
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
}
