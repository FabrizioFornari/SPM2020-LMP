package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id private String id;
	
	private String fristName;
	private String lastName;
	private String userName;
	private String hashedPassword;
	
	
	/*-----------ACCESSORY METHODS--------------*/
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
}
