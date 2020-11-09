package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spm.ParkMe.enums.Roles;

@Document(collection = "users")
public class User {
	
	@Id private String id;
	
	private String username;
	private String password;
	private String email;
	private Roles role;
	
	public User(String username, String email, String password, Roles role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
	/*-----------ACCESSORY METHODS--------------*/
	
	public String getId() {
		return this.id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
