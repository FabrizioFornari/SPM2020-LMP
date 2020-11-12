package com.spm.ParkMe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.spm.ParkMe.enums.Roles;

@SpringBootTest
public class DriverTest {
	
	private String validSSN;
	private String validEmail;
	private String validPhone;
	private String validPlate;
    
    private Driver driver;
	
	@BeforeEach
	public void setUp() {
		validSSN = "ZZZZZZ10A01A000Z";
		validEmail = "email@park.it";
		validPhone = "+393333333333";
		validPlate = "AA000AA";
		driver = new Driver(validEmail,"firstname", "lastname", validSSN, validPhone, validEmail, "password", validPlate, "car");
	}
	
	@Test
	public void createValidDriver() {
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
	
	//Plate
	public void createDriverWithNullPlateReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> driver.setPlate(null));
	}
	
	@Test
	public void createDriverWithEmptyPlateReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setPlate(""));
	}
	@Test
	public void createDriverWithInvalidPlateReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setPlate("AB 122 SC"));
	}
	
	//VehicleType
	public void cretaeDriverWithNullVehicleTypeReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> driver.setVehicleType(null));
	}
	
	@Test
	public void createDriverWithEmptyVehicleTypeReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setVehicleType(""));
	}

	
}
