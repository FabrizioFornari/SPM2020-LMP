package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;

public class Sensor {

	
	@NotNull(message="Street must not be null")
	@NotEmpty(message = "Street must not be empty")
	private String street;
	@NotNull(message="numberOfParkingLot must not be null")
	private Integer numberOfParkingLot;
	
	private SensorState state;
	
	
	
	//------- Constructor -----//
	public Sensor() {
		
	}
	
	public Sensor(String street, Integer numberOfParkingLot ) {
		this.setStreet(street);
		this.setNumberOfParkingLot(numberOfParkingLot);
		this.setState(SensorState.OFF);
	}
	
	//-------- accessor -----//
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		if(street != null && street != "" ) {
			this.street = street;
		}
		else {
			throw new IllegalArgumentException("Street is invalid");
			}	
		}
	
	public int getNumberOfParkingLot() {
		return numberOfParkingLot;
	}
	
	public void setNumberOfParkingLot(Integer numberOfParkingLot) {
		if(numberOfParkingLot != null) {
			this.numberOfParkingLot = numberOfParkingLot;
		}
		else {
			throw new IllegalArgumentException("numberOfParkingLot is invalid");
			}	
	}
	
	public SensorState getState() {
		return state;
	}

	public void setState(SensorState state) {
		this.state = state;
	}
	
}
