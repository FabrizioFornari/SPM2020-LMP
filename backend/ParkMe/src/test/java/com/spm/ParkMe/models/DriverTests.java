package com.spm.ParkMe.models;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriverTests {

	
	private String validSSN;
	private String validEmail;
	private String validPhone;
	private String validPlate;
	private ValidatorFactory validatorFactory;
    private Validator validator;
	
	@BeforeEach
	public void setUp() {
		validSSN = "ZZZZZZ10A01A000Z";
		validEmail = "email@park.it";
		validPhone = "+393333333333";
		validPlate = "AA000AA";
		validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
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
	
	@Test
	public void createDriverWithInvalidSSN() {
		Driver driver = new Driver("firstname", "lastname", "some invalid SSN", validEmail, validPhone, validPlate, "car", "password");
		System.out.println(driver.getSsn());
		Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
		assertEquals("Invalid ssn format", violations.iterator().next().getMessage());
	}
}
