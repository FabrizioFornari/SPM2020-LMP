package com.spm.ParkMe.models;

import java.util.List;

public class JwtResponse {
	
	private String token;
	private String id;
	private String username;
	private String email;
	private List<String> roles;
	
	public JwtResponse(String token, String id, String username, String email, List<String> roles) {
		this.setToken(token);
		this.setId(id);
		this.setUsername(username);
		this.setEmail(email);
		this.setRoles(roles);
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
