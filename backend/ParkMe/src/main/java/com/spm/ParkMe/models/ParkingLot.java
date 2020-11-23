package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;

public class ParkingLot {

	@Id private String id;
	private String street;
	private int numberOfParkingLot;
	private boolean isHandicapParkingLot;
	private double pricePerHours;
	private String typeOfVehicle;
	private Coordinates coordinates;
	
	/*-------Constructor------*/
	public ParkingLot() {
		
	}
	 
	public ParkingLot(String street, int numberOfParkingLot, boolean isHandicapParkingLot,double pricePerHours, String typeOfVehicle, Coordinates coordinates) {
		this.street=street;
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
		this.street = street;
	}
	public int getNumberOfParkingLot() {
		return numberOfParkingLot;
	}
	public void setNumberOfParkingLot(int numberOfParkingLot) {
		this.numberOfParkingLot = numberOfParkingLot;
	}
	public boolean isHandicapParkingLot() {
		return isHandicapParkingLot;
	}
	public void setHandicapParkingLot(boolean isHandicapParkingLot) {
		this.isHandicapParkingLot = isHandicapParkingLot;
	}
	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}
	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}
	public double getPricePerHours() {
		return pricePerHours;
	}
	public void setPricePerHours(double pricePerHours) {
		this.pricePerHours = pricePerHours;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}
