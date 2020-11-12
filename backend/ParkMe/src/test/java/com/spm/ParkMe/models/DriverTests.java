package com.spm.ParkMe.models;
//import static org.junit.Assert.assertThrows;
//import static org.junit.Assert.assertTrue;
import  org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriverTests {

	
	private String validSSN;
	private String validEmail;
	private String validPhone;
	private String validPlate;
	private ValidatorFactory validatorFactory;
    private Validator validator;
    
    private Driver driver;
	
	@BeforeEach
	public void setUp() {
		validSSN = "ZZZZZZ10A01A000Z";
		validEmail = "email@park.it";
		validPhone = "+393333333333";
		validPlate = "AA000AA";
		driver = new Driver("firstname", "lastname", validSSN, validEmail, validPhone, validPlate, "car", "password");
		validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
	}
	
	@Test
	public void createValidDriver() {
		//Driver driver = new Driver("firstname", "lastname", validSSN, validEmail, validPhone, validPlate, "car", "password");
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
	
	//firstname
	@Test
	public void createDriverWithNullFirstNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setFirstName(null));
	}
	
	@Test
	public void createDriverWithEmptyFirstNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setFirstName(null));
	}
	
	//lastname
	
	@Test
	public void createDriverWithNullLastNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setLastName(null));
	}
	
	@Test
	public void createDriverWithEmptyLastNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setLastName(""));
	}
	
	//ssn
	
	@Test
	public void createDriverWithNullSSNReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setSsn(null));
	}
	
	@Test
	public void createDriverWithEmptySSNReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setSsn(""));
	}
	
	@Test
	public void createDriverWithInvalidSSNReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setSsn("FGHGgvy66542d"));
	}
	
	//email
	public void creteDriverWithNullEmailReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> driver.setEmail(null));
	}
	
	@Test
	public void createDriverWithEmptyEmailReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setEmail(""));
	}
	@Test
	public void createDriverWithInvalidEmailReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setSsn("prova1.com"));
	}
	
	//Phone
	public void creteDriverWithNullPhoneReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> driver.setPhone(null));
	}
	
	@Test
	public void createDriverWithEmptyPhoneReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setPhone(""));
	}
	@Test
	public void createDriverWithInvalidPhoneReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setPhone("9735 302309"));
	}
	
	//Plate
	public void creteDriverWithNullPlateReturnsException() {
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
	public void creteDriverWithNullVehicleTypeReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> driver.setVehicleType(null));
	}
	
	@Test
	public void createDriverWithEmptyVehicleTypeReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setVehicleType(""));
	}

	//Password
	public void creteDriverWithNullPasswordReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> driver.setPassword(null));
	}
	
	@Test
	public void createDriverWithPasswordTypeReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> driver.setPassword(""));
	}
	
	
}
