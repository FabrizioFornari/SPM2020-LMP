package com.spm.ParkMe.models.requestBody;

import com.spm.ParkMe.enums.SensorState;

public class SensorChangeInfo {
	
	private String street;
	private Integer number;
	private SensorState state;
	
	public SensorChangeInfo() {}
	
	public SensorChangeInfo(String street, Integer number, SensorState state) {
		this.setStreet(street);
		this.setNumber(number);
		this.setState(state);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public SensorState getState() {
		return state;
	}

	public void setState(SensorState state) {
		this.state = state;
	}
}
