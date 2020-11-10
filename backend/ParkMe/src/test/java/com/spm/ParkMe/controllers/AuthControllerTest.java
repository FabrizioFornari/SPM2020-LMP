package com.spm.ParkMe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Credentials;
import com.spm.ParkMe.models.JwtResponse;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.security.services.UserDetailsImpl;
import com.spm.ParkMe.security.services.UserDetailsServiceImpl;
import com.sun.el.stream.Optional;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserDetailsServiceImpl userService;
	
	@InjectMocks
    private AuthController authController;
	
	
	private JacksonTester<Credentials> jsonCredentials;
	
	private Credentials driverCredentials = new Credentials("rocche@park.it", "Rocche");
	
	PasswordEncoder encoder;
	/*
	private Credentials vigilantCredentials = new Credentials("cret@park.it", "Cret");
	private Credentials parkingManagerCredentials = new Credentials("flash@park.it", "Flash");
	private Credentials adminCredentials = new Credentials("fusaro@turbomondialismo.it", "Fusaro");
	*/
	
	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
	}
	
	@Test
	public void loginDriverUser() throws Exception{
		System.out.println("PRONTI");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_DRIVER"));
		
		Mockito.when(userService.loadUserByUsername("rocche@park.it"))
         	.thenReturn(new UserDetailsImpl("id", "rocche@park.it", "rocche@park.it", encoder.encode("Rocche"), authorities));
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/auth/login").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonCredentials.write(driverCredentials).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals("/api/auth/login",
				response.getHeader(HttpHeaders.LOCATION));
	}

}
