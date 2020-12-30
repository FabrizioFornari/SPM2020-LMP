package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userSessions")
public class UserSession {

	@Id private String id;
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserSession userSession = (UserSession) o;
		return(userSession.getUser().equals(this.getUser()) &&
				userSession.getSessionID().equals(this.getSessionID()));
	}
}
