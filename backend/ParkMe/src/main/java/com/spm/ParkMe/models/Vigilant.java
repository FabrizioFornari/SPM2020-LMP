package com.spm.ParkMe.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.spm.ParkMe.enums.Roles;

@Document(collection = "users")
public class Vigilant extends User{
	
	public Vigilant() {}

	public Vigilant(String username, String firstName, String lastName, String ssn, String phone, String email,
			String password) {
		super(username, firstName, lastName, ssn, phone, email, password, Roles.ROLE_VIGILANT);
	}

}
