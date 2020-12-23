package com.spm.ParkMe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

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
import org.springframework.boot.test.context.SpringBootTest;
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
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.models.requestBody.Credentials;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.security.jwt.JwtUtils;
import com.spm.ParkMe.security.services.UserDetailsImpl;
import com.spm.ParkMe.security.services.UserDetailsServiceImpl;

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class AuthControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private WebApplicationContext context;
	
	@InjectMocks
    private AuthController authController;
	
	
	private JacksonTester<Credentials> jsonCredentials;
	
	//needed mocks to initialize in setup method
	private Credentials vigilantCredentials;
	
	@Autowired
	PasswordEncoder encoder;
	
	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
		Vigilant vigilant = VIGILANT_OBJECT;
		vigilant.setPassword(encoder.encode(VALID_PASSWORD));
		userRepository.save(vigilant);
		vigilantCredentials = new Credentials(VIGILANT_MAIL, VALID_PASSWORD);
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders
	        .webAppContextSetup(context)
	        .apply(springSecurity())
	        .build();
	}
	
	@Test
	public void loginDriverUserReturnsOKStatus() throws Exception{
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				AUTH_ENDPOINT + LOGIN_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonCredentials.write(vigilantCredentials).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void loginFails() throws Exception{
		
		vigilantCredentials.setEmail(DRIVER_MAIL);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				AUTH_ENDPOINT + LOGIN_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonCredentials.write(vigilantCredentials).getJson())
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

}
