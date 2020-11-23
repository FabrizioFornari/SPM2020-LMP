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
	public void setLatitude(Double latitude) {
		if(latitude != null  ) {
			this.latitude = latitude;
		}
		else {
			throw new IllegalArgumentException("Latitude is invalid");
			}
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		if(longitude != null ) {
			this.longitude = longitude;
		}
		else {
			throw new IllegalArgumentException("Longitude is invalid");
			}
	}
}
