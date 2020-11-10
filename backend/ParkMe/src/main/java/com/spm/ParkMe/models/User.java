package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spm.ParkMe.enums.Roles;

@Document(collection = "users")
public class User {
	
	@Id private String id;
	
	private String email;
	private String username;
	private String password;
	private Roles role;
	
	public User(String email, String username, String password, Roles role) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	
	/*-----------ACCESSORY METHODS--------------*/
	
	public String getId() {
		return this.id;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
}
