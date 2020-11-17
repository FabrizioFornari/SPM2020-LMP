package com.spm.ParkMe.controllers;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.spm.ParkMe.controllers.AdminControllerTest.WrongObject;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.Vigilant;
import com.spm.ParkMe.models.requestBody.ChangeMailInfo;
import com.spm.ParkMe.models.requestBody.ChangePasswordInfo;
import com.spm.ParkMe.models.requestBody.ChangePhoneInfo;
import com.spm.ParkMe.models.requestBody.ChangePlateVehicleTypeInfo;
import com.spm.ParkMe.repositories.UserRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;

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
	
	@Autowired
	PasswordEncoder encoder;
	
	private Vigilant testUser;
	private Driver testUserDriver;
	private ChangeMailInfo mailInfo;
	private ChangePasswordInfo passwordInfo;
	private ChangePhoneInfo phoneInfo;
	private ChangePlateVehicleTypeInfo plateVehicleTypeInfo;
	
	private MockMvc mockMvc;
	
	private JacksonTester<ChangeMailInfo> jsonMailInfo;
	private JacksonTester<ChangePasswordInfo> jsonPasswordInfo;
	private JacksonTester<ChangePhoneInfo> jsonPhoneInfo;
	private JacksonTester<ChangePlateVehicleTypeInfo> jsonPlateVehicleTypeInfo;
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
				"+39 333 3333333", "prova@park.it", encoder.encode("A"));
		
		testUserDriver= new Driver("rocche@park.it", "Giacomo", "Rocchetti", "ZZZZZZ10A01A000Z", 
				"+39 333 3333333","rocche@park.it",  encoder.encode("Rocche"), "AA000AA", "car");
		userRepository.save(testUser);
		userRepository.save(testUserDriver);
		
		mailInfo = new ChangeMailInfo("prova@park.it", "provetta@park.it");
		passwordInfo = new ChangePasswordInfo("A", "B");
		
		phoneInfo = new ChangePhoneInfo("+39 338 4283440", "+39 338 5555555");
		plateVehicleTypeInfo= new ChangePlateVehicleTypeInfo("AA000AA", "AA000CC", "car","scooter");
		mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
		JacksonTester.initFields(this, new ObjectMapper()); 
		 
		wrongObject = new WrongObject("");
	}
	
	//change email
	
	@Test
	public void emailChangeUnauthorizedWithoutToken() throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + EMAIL_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonMailInfo.write(mailInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
	public void emailChangeAuthorizedWithTokenAndCorrectUser() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				MODIFICATION_ENDPOINT + EMAIL_MODIFICATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonMailInfo.write(mailInfo).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		
		//check new mail
		User user = userRepository.findByUsername(mailInfo.getNewEmail()).orElseThrow();
		assertEquals(user.getUsername(), mailInfo.getNewEmail());
	}
	
	@Test
	@WithMockUser(username="altro@park.it", roles= {"VIGILANT"})
	public void emailChangeUnauthorizedWithTokenButWrongUser() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				MODIFICATION_ENDPOINT + EMAIL_MODIFICATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonMailInfo.write(mailInfo).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(roles= {"VIGILANT"})
	public void emailChangeWithWrongBodyReturnsBadRequest() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				MODIFICATION_ENDPOINT + EMAIL_MODIFICATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(wrongObject).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	//change password
	
	@Test
	public void passwordChangeUnauthorizedWithoutToken() throws Exception {
		
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + PASSWORD_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonPasswordInfo.write(passwordInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
	public void passwordChangeAuthorizedWithTokenAndCorrectPassword() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				MODIFICATION_ENDPOINT + PASSWORD_MODIFICATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonPasswordInfo.write(passwordInfo).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		//check if changed password is correct
		User user = userRepository.findByUsername("prova@park.it").orElseThrow();
		assertTrue(encoder.matches("B", user.getPassword()));
	}
	
	@Test
	@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
	public void passwordChangeUnauthorizedWithTokenButWrongCurrentPassword() throws Exception {
		
		passwordInfo.setCurrentPassword("C");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				MODIFICATION_ENDPOINT + PASSWORD_MODIFICATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonPasswordInfo.write(passwordInfo).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
	public void passwordChangeWithWrongBodyReturnsBadRequest() throws Exception {
	
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				MODIFICATION_ENDPOINT + PASSWORD_MODIFICATION_ENDPOINT).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonWrongObject.write(wrongObject).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	//phone
	
		@Test
		public void phoneChangeUnauthorizedWithoutToken() throws Exception {
			
				
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
						MODIFICATION_ENDPOINT + PHONE_MODIFICATION_ENDPOINT).accept(
						MediaType.APPLICATION_JSON)
						.content(jsonPhoneInfo.write(phoneInfo).getJson())
						.contentType(MediaType.APPLICATION_JSON);
				mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
		}
		
		
		
		@Test
		@WithMockUser(username="prova@park.it", roles= {"VIGILANT"})
		public void phoneChangeAuthorizedWithTokenAndCorrectUser()  throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + PHONE_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonPhoneInfo.write(phoneInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		}
	
		@Test
		@WithMockUser(roles= {"VIGILANT"})
		public void phoneChangeWithWrongBodyReturnsBadRequest() throws Exception {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + PHONE_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonWrongObject.write(wrongObject).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
		}
		
		
		
		//Plate and Vehicle Type
		@Test
		public void plateVehicleTypeChangeUnauthorizedWithoutToken() throws Exception {
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
						MODIFICATION_ENDPOINT + PLATE_VEHICLE_MODIFICATION_ENDPOINT).accept(
						MediaType.APPLICATION_JSON)
						.content(jsonPlateVehicleTypeInfo.write(plateVehicleTypeInfo).getJson())
						.contentType(MediaType.APPLICATION_JSON);
				mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
		}
		
		@Test
		@WithMockUser(username="rocche@park.it", roles= {"DRIVER"})
		public void plateVehicleTypeChangeAuthorizedWithTokenAndCorrectUser()  throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + PLATE_VEHICLE_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonPlateVehicleTypeInfo.write(plateVehicleTypeInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		}
		
		@Test
		@WithMockUser(username="altro@park.it", roles= {"VIGILANT"})
		public void plateVehicleTypeChangeUnauthorizedWithTokenButWrongUser() throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + PLATE_VEHICLE_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonPlateVehicleTypeInfo.write(plateVehicleTypeInfo).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isUnauthorized());
		}
		@Test
		@WithMockUser(roles= {"DRIVER"})
		public void plateVehicleTypeChangeWithWrongBodyReturnsBadRequest() throws Exception {
		
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					MODIFICATION_ENDPOINT + PLATE_VEHICLE_MODIFICATION_ENDPOINT).accept(
					MediaType.APPLICATION_JSON)
					.content(jsonWrongObject.write(wrongObject).getJson())
					.contentType(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest());
		}
		
		
		
}
