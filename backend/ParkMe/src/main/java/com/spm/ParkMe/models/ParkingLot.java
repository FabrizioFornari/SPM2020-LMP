package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.constants.RegexConstants;

public class ParkingLot {

	@Id private String id;
	@NotNull(message="Street must not be null")
	@NotEmpty(message = "Street must not be empty")
	private String street;
	@NotNull(message="numberOfParkingLot must not be null")
	private Integer numberOfParkingLot;
	@NotNull(message="isHandicapParkingLot must not be null")
	private Boolean isHandicapParkingLot;
	@NotNull(message="Price PerHours must not be null")
	private Double pricePerHours;
	@NotNull(message="type Of Vehicle must not be null")
	private String typeOfVehicle;
	private Coordinates coordinates;
	
	/*-------Constructor------*/
	public ParkingLot() {
		
	}
	 
	public ParkingLot(String street, Integer numberOfParkingLot, Boolean isHandicapParkingLot,Double pricePerHours, String typeOfVehicle, Coordinates coordinates) {
		this.setStreet(street);
		this.numberOfParkingLot=numberOfParkingLot;
		this.isHandicapParkingLot=isHandicapParkingLot;
		this.pricePerHours=pricePerHours;
		this.typeOfVehicle= typeOfVehicle;
		this.coordinates=coordinates;
	}
	
	/*------- ACCESSORY METHODS ---------*/
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
	
	public boolean getIsHandicapParkingLot() {
		return isHandicapParkingLot;
	}
	public void setHandicapParkingLot(Boolean isHandicapParkingLot) {
		if(isHandicapParkingLot != null) {
			this.isHandicapParkingLot = isHandicapParkingLot;
		}
		else {
			throw new IllegalArgumentException("isHandicapParkingLot is invalid");
			}	
	}
	
	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}
	public void setTypeOfVehicle(String typeOfVehicle) {
		if(typeOfVehicle != null) {
			this.typeOfVehicle = typeOfVehicle;
		}
		else {
			throw new IllegalArgumentException("typeOfVehicle is invalid");
			}
	}
	
	public double getPricePerHours() {
		return pricePerHours;
	}
	public void setPricePerHours(Double pricePerHours) {
		if(pricePerHours != null) {
			this.pricePerHours = pricePerHours;
		}
		else {
			throw new IllegalArgumentException("pricePerHours is invalid");
			}
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}
