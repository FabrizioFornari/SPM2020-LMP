package com.spm.ParkMe.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.Vigilant;


public final class UserInfoConstants {
	
	public static final String VALID_EMAIL = "prova@park.it";
	public static final String INVALID_EMAIL = "invalidEmail";
	public static final String VALID_PASSWORD = "validPassword";
	public static final String INVALID_PASSWORD = "";
	public static final String VALID_SSN = "ZZZZZZ10A01A000Z";
	public static final String INVALID_SSN = "LETMEINSERTACASUALSSN";
	public static final String VALID_PHONE = "+39 333 3333333";
	public static final String INVALID_PHONE = "12313";
	public static final String VALID_PLATE = "AA000AA";
	public static final String INVALID_PLATE = "MYPLATEISAWESOME";
	public static final String VEHICLE_TYPE = "4 Wheels Standard Vehicle";
	public static final String USERNAME = VALID_EMAIL;
	public static final String FIRSTNAME = "Name";
	public static final String LASTNAME = "Surname";
	
	@Autowired
	PasswordEncoder encoder;

	//create wrong class for testing 
		public static class WrongObject {
			private String randomAttribute;
			
			public WrongObject() {
				
			}
			public WrongObject(String randomAttribute) {
				this.setRandomAttribute(randomAttribute);
			}
			public String getRandomAttribute() {
				return randomAttribute;
			}
			public void setRandomAttribute(String randomAttribute) {
				this.randomAttribute = randomAttribute;
			}
		}
		
	public static final WrongObject WRONG_OBJECT = new WrongObject("randomAttribute");
	
	public static final String DRIVER_MAIL = "driver@park.it";
	public static final String VIGILANT_MAIL = "vigilant@park.it";
	public static final String PARKING_MANAGER_MAIL = "parking_manager@park.it";
	public static final String ADMIN_MAIL = "admin@park.it";
	
	public static final Driver DRIVER_OBJECT = new Driver(DRIVER_MAIL, FIRSTNAME, LASTNAME, VALID_SSN, VALID_PHONE, DRIVER_MAIL, VALID_PASSWORD, VALID_PLATE, VEHICLE_TYPE);
	public static final Vigilant VIGILANT_OBJECT = new Vigilant(VIGILANT_MAIL, FIRSTNAME, LASTNAME, VALID_SSN, VALID_PHONE, VIGILANT_MAIL, VALID_PASSWORD);
	public static final ParkingManager PARKING_MANAGER_OBJECT = new ParkingManager(PARKING_MANAGER_MAIL, FIRSTNAME, LASTNAME, VALID_SSN, VALID_PHONE, PARKING_MANAGER_MAIL, VALID_PASSWORD);
	public static final User ADMIN_OBJECT = new User(ADMIN_MAIL, FIRSTNAME, LASTNAME, VALID_SSN, VALID_PHONE, ADMIN_MAIL, VALID_PASSWORD, Roles.ROLE_ADMIN);

	

	
	
}