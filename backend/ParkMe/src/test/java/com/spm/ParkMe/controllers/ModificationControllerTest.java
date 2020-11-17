package com.spm.ParkMe.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.controllers.AdminControllerTest.WrongObject;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.models.requestBody.ChangeMailInfo;
import com.spm.ParkMe.models.requestBody.ChangePhoneInfo;
import com.spm.ParkMe.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class ModificationControllerTest {

	@InjectMocks
    private ModificationController modificationController;
	
    @Autowired
    private WebApplicationContext context;
	
	@Autowired
	UserRepository userRepository;
	
	private Vigilant testUser;
	private ChangeMailInfo mailInfo;
	
	private MockMvc mockMvc;
	
	private JacksonTester<ChangeMailInfo> jsonMailInfo;
	private WrongObject wrongObject;
	private JacksonTester<WrongObject> jsonWrongObject;
	
	
	
	
	
	//create wrong class for testing 
	public class WrongObject {
		private String firstName;
		
		public WrongObject() {
			
		}
		public WrongObject(String name) {
			this.setFirstName(name);
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	}
	
	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
		testUser = new Vigilant("prova@park.it", "A", "A", "RSSMRA80A01F205X",
				"+39 338 4283440", "prova@park.it", "A");
		userRepository.save(testUser);
		mailInfo = new ChangeMailInfo("prova@park.it", "provetta@park.it");
		mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
		JacksonTester.initFields(this, new ObjectMapper()); 
		 
		wrongObject = new WrongObject("");
	}
	
	@Test
	public void emailChangeUnauthorizedWithoutToken() throws Exception {
		
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					"/api/modify/email").accept(
					MediaType.APPLICATION_JSON)
					.content(jsonMailInfo.write(mailInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
	public void emailChangeAuthorizedWithTokenAndCorrectUser() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/modify/email").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonMailInfo.write(mailInfo).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(username="altro@park.it", roles= {"VIGILANT"})
	public void emailChangeUnauthorizedWithTokenButWrongUser() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/modify/email").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonMailInfo.write(mailInfo).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(roles= {"VIGILANT"})
	public void emailChangeWithWrongBodyReturnsBadRequest() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/api/modify/email").accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(wrongObject).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	
	
	
	private JacksonTester<ChangePhoneInfo> jsonPhoneInfo;
	private WrongObjectPhone wrongObjectPhone;
	private ChangePhoneInfo phoneInfo;
	private JacksonTester<WrongObjectPhone> jsonWrongObjectPhone;
	
	
	//create wrong class for testing 
		public class WrongObjectPhone {
			private String phone;
			
			public WrongObjectPhone() {
				
			}
			public WrongObjectPhone(String name) {
				this.setFirstName(name);
			}
			public String getFirstName() {
				return phone;
			}
			public void setFirstName(String firstName) {
				this.phone = firstName;
			}
		}
		
		@BeforeEach
		public void setUpPhone() {
			userRepository.deleteAll();
			testUser = new Vigilant("prova@park.it", "A", "A", "RSSMRA80A01F205X",
					"+39 338 4283440", "prova@park.it", "A");
			userRepository.save(testUser);
			phoneInfo = new ChangePhoneInfo("+39 338 4283440", "+39 338 5555555");
			mockMvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .apply(springSecurity())
	                .build();
			JacksonTester.initFields(this, new ObjectMapper()); 
			 
			wrongObjectPhone = new WrongObjectPhone("");
		}
		
		@Test
		public void phoneChangeUnauthorizedWithoutToken() throws Exception {
			
				
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
						"/api/modify/phone").accept(
						MediaType.APPLICATION_JSON)
						.content(jsonPhoneInfo.write(phoneInfo).getJson())
						.contentType(MediaType.APPLICATION_JSON);
				mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
		}
		
		
		
		@Test
		@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
		public void phoneChangeAuthorizedWithTokenAndCorrectUser()  throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					"/api/modify/phone").accept(
					MediaType.APPLICATION_JSON)
					.content(jsonPhoneInfo.write(phoneInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		}
		
		@Test
		@WithMockUser(username="altro@park.it", roles= {"VIGILANT"})
		public void phoneChangeUnauthorizedWithTokenButWrongUser() throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					"/api/modify/phone").accept(
					MediaType.APPLICATION_JSON)
					.content(jsonPhoneInfo.write(phoneInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
		}
	
		@Test
		@WithMockUser(roles= {"VIGILANT"})
		public void phoneChangeWithWrongBodyReturnsBadRequest() throws Exception {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					"/api/modify/phone").accept(
					MediaType.APPLICATION_JSON)
					.content(jsonWrongObjectPhone.write(wrongObjectPhone).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
		}
		
	
}
