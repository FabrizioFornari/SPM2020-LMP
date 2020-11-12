package com.spm.ParkMe.models;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriverTests {

	
	private String validSSN;
	private String validEmail;
	private String validPhone;
	private String validPlate;
	
	@BeforeEach
	public void setUp() {
		validSSN = "ZZZZZZ10A01A000Z";
		validEmail = "email@park.it";
		validPhone = "+39 3333333333";
		validPlate = "AA000AA";
	}
	
	@Test
	public void createValidDriver() {
		Driver driver = new Driver("firstname", "lastname", validSSN, validEmail, validPhone, validPlate, "car", "password");
		assertEquals(driver.getClass(), Driver.class);
		assertEquals(driver.getFirstName(), "firstname");
		assertEquals(driver.getLastName(), "lastname");
		assertEquals(driver.getSsn(), validSSN);
		assertEquals(driver.getEmail(), validEmail);
		assertEquals(driver.getPhone(), validPhone);
		assertEquals(driver.getPlate(), validPlate);
		assertEquals(driver.getVehicleType(), "car");
		assertEquals(driver.getPassword(), "password");
	}
}
