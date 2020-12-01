package com.spm.ParkMe.models.requestBody;

import com.spm.ParkMe.models.Coordinates;

public class StreetInfo {
	
	private String street;
	private Coordinates coordinates;
	
	public StreetInfo(String street, Coordinates coordinates){
		this.setStreet(street);
		this.setCoordinates(coordinates);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}
