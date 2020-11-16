package com.spm.ParkMe.models;

import java.util.List;

import com.spm.ParkMe.enums.Roles;

public class JwtResponse {
	
	private String token;
	private String id;
	private String username;
	private String email;
	private String ssn;
	private String phone;
	private String firstName;
	private String lastName;
	private String plate;
	private String vehicleType;
	
	private String role;
	
	public JwtResponse(String token, User user) {
		this.setToken(token);
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setEmail(user.getEmail());
		this.setSsn(user.getSsn());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setPhone(user.getPhone());
		this.setRoles(user.getRole().name());
		this.plate = null;
		this.vehicleType = null;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return role;
	}

	public void setRoles(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
