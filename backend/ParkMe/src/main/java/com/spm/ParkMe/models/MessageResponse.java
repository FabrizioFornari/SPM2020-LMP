package com.spm.ParkMe.models;

public class MessageResponse {

	private String message;

	//Costructor
	public MessageResponse(String message) {
		this.message = message;
		
	}
	
	//Accessor
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
