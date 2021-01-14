package com.spm.ParkMe.models.requestBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RefreshTicketInfo {
	
	@NotNull(message="")
	@NotEmpty(message = "")
	private String street;
	@NotNull(message="")
	private Integer numberOfParkingLot;
	@NotNull(message="")
	private Integer extraHours;

	public RefreshTicketInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public RefreshTicketInfo(String street, Integer numberOfParkingLot,Integer extraHours) {
		this.setStreet(street);
		this.setNumberOfParkingLot(numberOfParkingLot);
		this.setExtraHours(extraHours);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getExtraHours() {
		return extraHours;
	}

	public void setExtraHours(Integer extraHours) {
		this.extraHours = extraHours;
	}

	public Integer getNumberOfParkingLot() {
		return numberOfParkingLot;
	}

	public void setNumberOfParkingLot(Integer numberOfParkingLot) {
		this.numberOfParkingLot = numberOfParkingLot;
	}

}
