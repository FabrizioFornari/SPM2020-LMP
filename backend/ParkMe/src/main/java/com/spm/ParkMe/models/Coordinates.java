package com.spm.ParkMe.models;

import javax.validation.constraints.NotNull;

public class Coordinates {

	@NotNull(message="Latitude must not be null")
	private double latitude;
	@NotNull(message="Longitude must not be null")
	private double longitude;
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
