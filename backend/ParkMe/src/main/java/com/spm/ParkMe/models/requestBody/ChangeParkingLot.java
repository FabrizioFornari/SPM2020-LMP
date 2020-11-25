package com.spm.ParkMe.models.requestBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.spm.ParkMe.models.ParkingLot;

public class ChangeParkingLot {

	@NotNull(message="street must not be null")
	@NotEmpty(message = "street must not be empty")
	private String street;
	@NotNull(message="numberOfParkingLot must not be null")
	private Integer numberOfParkingLot;
	@NotNull(message="parkinglot must not be null")
	private ParkingLot parkinglot;
	
	/*-------Constructor------*/
	public ChangeParkingLot() {
		
	}
	
	public ChangeParkingLot(String street, Integer numberOfParkingLot, ParkingLot parkinglot ) {
		this.setNumberOfParkingLot(numberOfParkingLot);
		this.setParkinglot(parkinglot);
		this.setStreet(street);
	}	
	
	
	/*------- ACCESSORY METHODS ---------*/

	public Integer getNumberOfParkingLot() {
		return numberOfParkingLot;
	}
	public void setNumberOfParkingLot(Integer numberOfParkingLot) {
		if(numberOfParkingLot != null  ) {
			this.numberOfParkingLot = numberOfParkingLot;
		}
		else {
			throw new IllegalArgumentException("numberOfParkingLot is invalid");
			}
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		if(street != null && street!="" ) {
			this.street = street;
		}
		else {
			throw new IllegalArgumentException("street is invalid");
			}
	}
	
	public ParkingLot getParkinglot() {
		return parkinglot;
	}
	public void setParkinglot(ParkingLot parkinglot) {
		if(parkinglot != null  ) {
			this.parkinglot = parkinglot;
		}
		else {
			throw new IllegalArgumentException("parkinglot is invalid");
			}
	}
}
