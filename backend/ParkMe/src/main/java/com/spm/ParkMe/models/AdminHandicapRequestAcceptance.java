package com.spm.ParkMe.models;

import javax.validation.constraints.NotNull;

public class AdminHandicapRequestAcceptance {

	@NotNull
	private String username;
	@NotNull
	private boolean isAccepted;
	
	
	public AdminHandicapRequestAcceptance(String username, boolean isAccepted ) {
		this.username=username;
		this.isAccepted=isAccepted;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getIsAccepted() {
		return isAccepted;
	}
	
	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	
	
}
