package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.enums.Roles;

public class User {
	
	@Id private String id;
	
	private String username;
	private String hashedPassword;
	private Roles role;
	
	public User(String username, String hashedPassword, Roles role) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.setRole(role);
	}
	
	
	/*-----------ACCESSORY METHODS--------------*/
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	
}
