package com.spm.ParkMe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Credentials;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.security.jwt.JwtUtils;
import com.spm.ParkMe.security.services.UserDetailsImpl;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	JwtUtils jwtUtils;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
    private AuthController authController;
	
	
	private JacksonTester<Credentials> jsonCredentials;
	
	//needed mocks to initialize in setup method
	private Credentials driverCredentials;
	private User driverUser;
	private UserDetailsImpl principal;
	private List<GrantedAuthority> authorities;
	
	@Autowired
	PasswordEncoder encoder;
	/*
	private Credentials vigilantCredentials = new Credentials("cret@park.it", "Cret");
	private Credentials parkingManagerCredentials = new Credentials("flash@park.it", "Flash");
	private Credentials adminCredentials = new Credentials("fusaro@turbomondialismo.it", "Fusaro");
	*/
	
	@BeforeEach
	public void setUp() {
		driverCredentials = new Credentials("rocche@park.it", "Rocche");
		driverUser = new User("rocche@park.it", "rocche@park.it", "Rocche", Roles.ROLE_DRIVER);
		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_DRIVER"));
		principal = new UserDetailsImpl("id", "rocche@park.it", "rocche@park.it",  "Rocche", authorities);
		JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
	}
	
	@Test
	public void loginDriverUserReturnsOKStatus() throws Exception{
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null);
		
		Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class))).thenReturn(authentication);
		
		Mockito.when(jwtUtils.generateJwtToken(Mockito.any(Authentication.class))).thenReturn("xxx.yyy.zzz");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/auth/login").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonCredentials.write(driverCredentials).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}