package com.spm.ParkMe.models;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spm.ParkMe.constants.RegexConstants;

@Document(collection = "handicapPermitsRequests")
public class HandicapPermitsRequest {
	
	@Id private String id;
	
	@NotNull(message="Username must not be null")
	private String username;
	
	@NotNull(message="Timestamp must not be null")
	private long timestamp;
	
	public HandicapPermitsRequest(String username, long timestamp) {
		
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
}
