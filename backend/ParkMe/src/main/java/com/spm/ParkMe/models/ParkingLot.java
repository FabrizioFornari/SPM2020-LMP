package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.constants.RegexConstants;
import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;

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
	private Double pricePerHour;
	@NotNull(message="type Of Vehicle must not be null")
	private String typeOfVehicle;
	@NotNull(message="Coordinates must not be null")
	private Coordinates coordinates;
	
	private Status status;
	private SensorState sensorState;
	
	/*-------Constructor------*/
	public ParkingLot() {
		
	}
	 
	public ParkingLot(String street, Integer numberOfParkingLot, Boolean isHandicapParkingLot,Double pricePerHours, String typeOfVehicle, Coordinates coordinates) {
		this.setStreet(street);
		this.setNumberOfParkingLot(numberOfParkingLot);
		this.setIsHandicapParkingLot(isHandicapParkingLot);
		this.setPricePerHour(pricePerHours);
		this.setTypeOfVehicle(typeOfVehicle);
		this.setCoordinates(coordinates);
		this.setStatus(Status.FREE);
		this.setSensorState(SensorState.OFF);
	}
	
	/*------- ACCESSORY METHODS ---------*/
	public String getId() {
		return id;
	}
	
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
	public void setIsHandicapParkingLot(Boolean isHandicapParkingLot) {
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
	
	public double getPricePerHour() {
		return pricePerHour;
	}
	public void setPricePerHour(Double pricePerHour) {
		if(pricePerHour != null) {
			this.pricePerHour = pricePerHour;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ParkingLot parkingLot = (ParkingLot) o;
		return(parkingLot.getStreet().equals(this.getStreet()) &&
				((Boolean)parkingLot.getIsHandicapParkingLot()).equals((Boolean)this.getIsHandicapParkingLot()) &&
				parkingLot.getCoordinates().equals(this.getCoordinates()) &&
				((Integer)parkingLot.getNumberOfParkingLot()).equals((Integer)this.getNumberOfParkingLot())&&
				((Double) parkingLot.getPricePerHour()).equals((Double) this.getPricePerHour())&&
				parkingLot.getStatus().equals( this.getStatus())&&
				parkingLot.getTypeOfVehicle().equals(this.getTypeOfVehicle()));
		
	}

	public SensorState getSensorState() {
		return sensorState;
	}

	public void setSensorState(SensorState sensorState) {
		this.sensorState = sensorState;
	}

}
