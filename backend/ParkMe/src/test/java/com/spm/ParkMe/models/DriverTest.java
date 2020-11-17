package com.spm.ParkMe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.spm.ParkMe.enums.Roles;

import static com.spm.ParkMe.constants.UserInfoConstants.*;

@SpringBootTest
public class DriverTest {
    
    private Driver driver;
	
	@BeforeEach
	public void setUp() {
		driver = new Driver(VALID_EMAIL, FIRSTNAME, LASTNAME, VALID_SSN, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_PLATE, VEHICLE_TYPE);
	}
	
	@Test
	public void createValidDriver() {
		assertEquals(driver.getClass(), Driver.class);
		assertEquals(driver.getFirstName(), FIRSTNAME);
		assertEquals(driver.getLastName(), LASTNAME);
		assertEquals(driver.getSsn(), VALID_SSN);
		assertEquals(driver.getEmail(), VALID_EMAIL);
		assertEquals(driver.getPhone(), VALID_PHONE);
		assertEquals(driver.getPlate(), VALID_PLATE);
		assertEquals(driver.getVehicleType(), VEHICLE_TYPE);
		assertEquals(driver.getPassword(), VALID_PASSWORD);
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
		assertThrows(IllegalArgumentException.class, () -> driver.setPlate(INVALID_PLATE));
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
