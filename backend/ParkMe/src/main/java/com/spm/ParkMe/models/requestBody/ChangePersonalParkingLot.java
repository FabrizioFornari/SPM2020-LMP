package com.spm.ParkMe.models.requestBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChangePersonalParkingLot {
	
	@NotNull(message="street must not be null")
	@NotEmpty(message = "street must not be empty")
	private String oldStreet;
	
	@NotNull(message="numberOfParkingLot must not be null")
	private Integer oldNumberOfParkingLot;
	
	private String newStreet;
	private Integer newNumberOfParkingLot;
	private Boolean newIsHandicapParkingLot;
	private Double newPrice;
	private String newTypeOfVehicle;
	private String newLatitude;
	private String newLongitude;
	
	public ChangePersonalParkingLot(String street, Integer numberOfParkingLot, String newStreet, Integer newNumberOfParkingLot, Boolean newIsHandicapParkingLot, Double newPrice, String newTypeOfVehicle
			, String newLatitude, String newLongitude) {
		this.setOldNumberOfParkingLot(numberOfParkingLot);
		this.setOldStreet(street);
		this.setNewStreet(newStreet);
		this.setNewNumberOfParkingLot(newNumberOfParkingLot);
		this.setNewIsHandicapParkingLot(newIsHandicapParkingLot);
		this.setNewPrice(newPrice);
		this.setNewTypeOfVehicle(newTypeOfVehicle);
		this.setNewLatitude(newLatitude);
		this.setNewLongitude(newLongitude);
	}
	
	public Integer getOldNumberOfParkingLot() {
		return oldNumberOfParkingLot;
	}
	public void setOldNumberOfParkingLot(Integer numberOfParkingLot) {
		if(numberOfParkingLot != null  ) {
			this.oldNumberOfParkingLot = numberOfParkingLot;
		}
		else {
			throw new IllegalArgumentException("numberOfParkingLot is invalid");
			}
	}
	
	public String getOldStreet() {
		return oldStreet;
	}
	public void setOldStreet(String street) {
		if(street != null && street!="" ) {
			this.oldStreet = street;
		}
		else {
			throw new IllegalArgumentException("street is invalid");
			}
	}

	
	public String getNewStreet() {
		return newStreet;
	}
	public void setNewStreet(String newStreet) {
		this.newStreet = newStreet;
	}
	public Integer getNewNumberOfParkingLot() {
		return newNumberOfParkingLot;
	}
	public void setNewNumberOfParkingLot(Integer newNumberOfParkingLot) {
		this.newNumberOfParkingLot = newNumberOfParkingLot;
	}
	public Boolean getNewIsHandicapParkingLot() {
		return newIsHandicapParkingLot;
	}
	public void setNewIsHandicapParkingLot(Boolean newIsHandicapParkingLot) {
		this.newIsHandicapParkingLot = newIsHandicapParkingLot;
	}
	public Double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}
	public String getNewTypeOfVehicle() {
		return newTypeOfVehicle;
	}
	public void setNewTypeOfVehicle(String newTypeOfVehicle) {
		this.newTypeOfVehicle = newTypeOfVehicle;
	}
	public String getNewLatitude() {
		return newLatitude;
	}
	public void setNewLatitude(String newLatitude) {
		this.newLatitude = newLatitude;
	}
	public String getNewLongitude() {
		return newLongitude;
	}
	public void setNewLongitude(String newLongitude) {
		this.newLongitude = newLongitude;
	}

}
