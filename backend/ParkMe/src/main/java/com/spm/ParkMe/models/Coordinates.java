package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Coordinates {

	@NotNull(message="Latitude must not be null")
	@NotEmpty(message = "Latitude must not be empty")
	private String latitude;
	@NotNull(message="Longitude must not be null")
	@NotEmpty(message = "Longitude must not be empty")
	private String longitude;
	
	
	
	/*-------Constructor------*/
	public Coordinates() {
		
	}
	public Coordinates(String latitude, String longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
	
	/*------- ACCESSORY METHODS ---------*/
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		if(latitude != null && latitude !="" ) {
			this.latitude = latitude;
		}
		else {
			throw new IllegalArgumentException("Latitude is invalid");
			}
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		if(longitude != null && longitude !="" ) {
			this.longitude = longitude;
		}
		else {
			throw new IllegalArgumentException("Longitude is invalid");
			}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Coordinates coordinates = (Coordinates) o;
		return(coordinates.getLatitude().equals(this.getLatitude()) &&
				coordinates.getLongitude().equals(this.getLongitude())); 
		
	}
}
