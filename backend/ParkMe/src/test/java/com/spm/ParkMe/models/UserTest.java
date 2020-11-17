package com.spm.ParkMe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.spm.ParkMe.constants.UserInfoConstants.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import com.spm.ParkMe.enums.Roles;

@SpringBootTest
public class UserTest {

    private User user;
	
	@BeforeEach
	public void setUp() {
		user = new User(VALID_EMAIL, FIRSTNAME, LASTNAME, VALID_SSN, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, Roles.ROLE_DRIVER);
	}
	
	@Test
	public void createValidDriver() {
		assertEquals(user.getClass(), User.class);
		assertEquals(user.getUsername(), VALID_EMAIL);
		assertEquals(user.getFirstName(), FIRSTNAME);
		assertEquals(user.getLastName(), LASTNAME);
		assertEquals(user.getSsn(), VALID_SSN);
		assertEquals(user.getEmail(), VALID_EMAIL);
		assertEquals(user.getPhone(), VALID_PHONE);
		assertEquals(user.getPassword(), VALID_PASSWORD);
	}
	
	//firstname
	@Test
	public void createUserWithNullFirstNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setFirstName(null));
	}
	
	@Test
	public void createUserWithEmptyFirstNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setFirstName(""));
	}
	
	//lastname
	
	@Test
	public void createUserWithNullLastNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setLastName(null));
	}
	
	@Test
	public void createUserWithEmptyLastNameReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setLastName(""));
	}
	
	//ssn
	
	@Test
	public void createUserWithNullSSNReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setSsn(null));
	}
	
	@Test
	public void createUserWithEmptySSNReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setSsn(""));
	}
	
	@Test
	public void createUserWithInvalidSSNReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setSsn(INVALID_SSN));
	}
	
	//email
	public void createUserWithNullEmailReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> user.setEmail(null));
	}
	
	@Test
	public void createUserWithEmptyEmailReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));
	}
	
	@Test
	public void createUserWithInvalidEmailReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setEmail(INVALID_EMAIL));
	}
	
	//Phone
	public void createUserWithNullPhoneReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> user.setPhone(null));
	}
	
	@Test
	public void createUserWithEmptyPhoneReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setPhone(""));
	}
	@Test
	public void createUserWithInvalidPhoneReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setPhone(INVALID_PHONE));
	}
	
	//Password
	public void createUserWithNullPasswordReturnsException() {
		assertThrows(IllegalArgumentException.class,()-> user.setPassword(null));
	}
	
	@Test
	public void createUserWithPasswordTypeReturnsException() {
		assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));
	}
}
