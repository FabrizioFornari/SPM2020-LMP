package com.spm.ParkMe.models;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "handicapPermitsRequests")
public class HandicapPermitsRequest {
	
	@Id private String id;
	
	@NotNull(message="Username must not be null")
	private String username;
	
	@NotNull(message="Timestamp must not be null")
	private long timestamp;
	
	private boolean isAccepted;
	
	private boolean isProcessed;
	
	public HandicapPermitsRequest() {}
	
	public HandicapPermitsRequest(String username, long timestamp, boolean isAccepted, boolean isProcessed) {
		this.setUsername(username);
		this.setTimestamp(timestamp);
		this.setAccepted(isAccepted);
		this.setProcessed(isProcessed);
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		if(username != null) {
			this.username = username;
		}
		else {
			throw new IllegalArgumentException("Username is invalid");
		}
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		if(timestamp != null) {
			this.timestamp = timestamp;
		}
		else {
			throw new IllegalArgumentException("Timestamp is invalid");
		}
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
}
