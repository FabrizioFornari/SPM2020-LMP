package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.ParkingLotCostants.CAR;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_COORDINATES;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_ISHANDICAPPARKINGLOT;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_NUMBROFPARKINGLOT;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_PRICEPERHOURS;
import static com.spm.ParkMe.constants.ParkingLotCostants.VALID_STREET;
import static com.spm.ParkMe.constants.UserInfoConstants.DRIVER_OBJECT;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.NotificationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class NotificationControllerTest {

	@Autowired
	NotificationRepository notificationRepository;
	
	private Notification notification;
	private MockMvc mockMvc;
	private JacksonTester<Notification> jsonNotification;
	
	@BeforeEach
	public void setUp() {
		 
	}
}
