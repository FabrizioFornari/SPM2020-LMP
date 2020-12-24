package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.ParkingLotCostants.CAR;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_COORDINATES;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_ISHANDICAPPARKINGLOT;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_NUMBROFPARKINGLOT;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_PRICEPERHOURS;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_STREET;
import static com.spm.ParkMe.constants.UserInfoConstants.DRIVER_MAIL;
import static com.spm.ParkMe.constants.UserInfoConstants.DRIVER_OBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;

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
import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.requestBody.ChangeNotificationStatusInfo;
import com.spm.ParkMe.repositories.NotificationRepository;

import static com.spm.ParkMe.constants.NotificationConstants.*;

import static com.spm.ParkMe.constants.EndpointContants.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class NotificationControllerTest {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
    private WebApplicationContext context;
	
	private Notification notification;
	private MockMvc mockMvc;
	private JacksonTester<ChangeNotificationStatusInfo> jsonNotificationStatusInfo;
	
	
	@BeforeEach
	public void setUp() {
		
			notificationRepository.deleteAll();
			
			notificationRepository.save(NOTIFICATION_1);
			notificationRepository.save(NOTIFICATION_2);

			mockMvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .apply(springSecurity())
	                .build();
			JacksonTester.initFields(this, new ObjectMapper()); 
	}
	
	@Test
	@WithMockUser(username = USERNAME, roles= {"DRIVER"})
	public void modifyNotificatinStatusToRead() throws Exception {
	
		Notification notification=notificationRepository.findAll().get(0);
		ChangeNotificationStatusInfo changeNotification= new ChangeNotificationStatusInfo(notification.getId(), StatusNotification.READ);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				NOTIFICATION_ENDPOINT + NOTIFICATION_SET_STATUS).accept(
				MediaType.APPLICATION_JSON)
				.content(jsonNotificationStatusInfo.write(changeNotification).getJson())
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@WithMockUser(username = USERNAME, roles= {"DRIVER"})
	public void getAllUserNotificationsReturnsListNotifications() throws Exception {
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				NOTIFICATION_ENDPOINT + NOTIFICATION_GET_ALL_USER_NOTIFICATIONS).accept(
				MediaType.APPLICATION_JSON).param("username", USERNAME)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		List<Notification> notifications= notificationRepository.findByUsername(USERNAME);
		assertEquals(2, notifications.size());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
}
