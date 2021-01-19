package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.constants.RegexConstants;
import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;

public class ParkingLot extends Parking{

	
	@NotNull(message="Price PerHours must not be null")
	private Double pricePerHour;	
	
	private Status status;

	
	/*-------Constructor------*/
	public ParkingLot() {
		super();
	}
	 
	public ParkingLot(String street, Integer numberOfParkingLot, Boolean isHandicapParkingLot,Double pricePerHour, String typeOfVehicle, Coordinates coordinates) {
		super(street,numberOfParkingLot,isHandicapParkingLot,typeOfVehicle,coordinates);
		this.setPricePerHour(pricePerHour);
		this.setStatus(Status.FREE);
		
	}
	
	
	
	public double getPricePerHour() {
		return pricePerHour;
	}
	public void setPricePerHour(Double pricePerHour) {
		if(pricePerHour != null) {
			this.pricePerHour = pricePerHour;
		}
		else {
			throw new IllegalArgumentException("pricePerHour is invalid");
			}
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


}
