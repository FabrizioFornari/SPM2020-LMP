package com.spm.ParkMe.models;

import javax.validation.constraints.NotNull;

public class Coordinates {

	@NotNull(message="Latitude must not be null")
	private Double latitude;
	@NotNull(message="Longitude must not be null")
	private Double longitude;
	
	
	
	/*-------Constructor------*/
	public Coordinates() {
		
	}
	public Coordinates(Double latitude, Double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	/*------- ACCESSORY METHODS ---------*/
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
