package com.spm.ParkMe.models;

public class UserSession {

	private User user;
	private String sessionID;
	
	public UserSession() {}
	
	public UserSession(User user, String sessionID) {
		this.setUser(user);
		this.setSessionID(sessionID);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
}
