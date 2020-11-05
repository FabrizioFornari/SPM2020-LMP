package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id private String id;
	
	private String firstName;
	private String lastName;
	private String username;
	private String hashedPassword;
	
	public User(String firstName, String lastName, String username, String hashedPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.hashedPassword = hashedPassword;
	}
	
	
	/*-----------ACCESSORY METHODS--------------*/
	public String getFristName() {
		return firstName;
	}
	public void setFristName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
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
	
}
