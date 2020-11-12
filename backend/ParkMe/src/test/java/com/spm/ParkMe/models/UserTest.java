package com.spm.ParkMe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import com.spm.ParkMe.enums.Roles;

@SpringBootTest
public class UserTest {

	private String validSSN;
	private String validEmail;
	private String validPhone;
    
    private User user;
	
	@BeforeEach
	public void setUp() {
		validSSN = "ZZZZZZ10A01A000Z";
		validEmail = "email@park.it";
		validPhone = "+39 333 3333333";
		user = new User(validEmail,"firstname", "lastname", validSSN, validPhone, validEmail, "password", Roles.ROLE_DRIVER);
	}
	
	@Test
	public void createValidDriver() {
		assertEquals(user.getClass(), User.class);
		assertEquals(user.getUsername(), validEmail);
		assertEquals(user.getFirstName(), "firstname");
		assertEquals(user.getLastName(), "lastname");
		assertEquals(user.getSsn(), validSSN);
		assertEquals(user.getEmail(), validEmail);
		assertEquals(user.getPhone(), validPhone);
		assertEquals(user.getPassword(), "password");
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
		assertThrows(IllegalArgumentException.class, () -> user.setSsn("FGHGgvy66542d"));
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
		assertThrows(IllegalArgumentException.class, () -> user.setEmail("prova1.com"));
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
		assertThrows(IllegalArgumentException.class, () -> user.setPhone("9735 302309"));
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
