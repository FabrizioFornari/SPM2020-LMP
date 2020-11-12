package com.spm.ParkMe.models;

import com.spm.ParkMe.enums.Roles;

public class ParkingManager extends User{

	public ParkingManager(String username, String firstName, String lastName, String ssn, String phone, String email,
			String password) {
		super(username, firstName, lastName, ssn, phone, email, password, Roles.ROLE_PARKING_MANAGER);
	}

}
