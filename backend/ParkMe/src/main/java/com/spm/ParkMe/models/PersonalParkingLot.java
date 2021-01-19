package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.enums.PersonalParkingLotStatus;
import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;

public class PersonalParkingLot extends Parking{

	
	@NotNull(message="Street must not be null")
	@NotEmpty(message = "Street must not be empty")
	private Double price;
	private PersonalParkingLotStatus status;
	
	
	public PersonalParkingLot() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public PersonalParkingLot(String street, Integer numberOfParkingLot, Boolean isHandicapParkingLot,Double price, String typeOfVehicle, Coordinates coordinates) {
		super(street,numberOfParkingLot,isHandicapParkingLot,typeOfVehicle,coordinates);
		this.setPrice(price);;
		this.setPersonalParkingLotStatus(PersonalParkingLotStatus.FREE);
		
	}
	
	
	/*------- ACCESSORY METHODS ---------*/
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		if(price != null) {
			this.price = price;
		}
		else {
			throw new IllegalArgumentException("pricePerHours is invalid");
			}
	}
	

	public PersonalParkingLotStatus getPersonalParkingLotStatus() {
		return status;
	}

	public void setPersonalParkingLotStatus(PersonalParkingLotStatus status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PersonalParkingLot parkingLot = (PersonalParkingLot) o;
		return(parkingLot.getStreet().equals(this.getStreet()) &&
				((Boolean)parkingLot.getIsHandicapParkingLot()).equals((Boolean)this.getIsHandicapParkingLot()) &&
				parkingLot.getCoordinates().equals(this.getCoordinates()) &&
				((Integer)parkingLot.getNumberOfParkingLot()).equals((Integer)this.getNumberOfParkingLot())&&
				((Double) parkingLot.getPrice()).equals((Double) this.getPrice())&&
				parkingLot.getPersonalParkingLotStatus().equals(this.getPersonalParkingLotStatus())&&
				parkingLot.getTypeOfVehicle().equals(this.getTypeOfVehicle()));
		
	}



	

}
