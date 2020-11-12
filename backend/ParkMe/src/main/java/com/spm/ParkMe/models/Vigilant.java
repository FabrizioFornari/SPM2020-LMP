package com.spm.ParkMe.models;

import com.spm.ParkMe.enums.Roles;

public class Vigilant extends User{

	public Vigilant(String username, String firstName, String lastName, String ssn, String phone, String email,
			String password) {
		super(username, firstName, lastName, ssn, phone, email, password, Roles.ROLE_VIGILANT);
	}

}
