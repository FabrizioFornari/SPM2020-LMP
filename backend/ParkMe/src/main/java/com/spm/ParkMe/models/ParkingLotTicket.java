package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParkingLotTicket {

	@NotNull(message="Street must not be null")
	@NotEmpty(message = "Street must not be empty")
	private String street;
	@NotNull(message="numberOfParkingLot must not be null")
	private Integer numberOfParkingLot;
	@NotNull(message="username must not be null")
	@NotEmpty(message = "username must not be empty")
	private String username;
	@NotNull(message="moneySpent must not be null")
	private Double moneySpent;
	@NotNull(message="Timestamp must not be null")
	private long expiringTimestamp;
	
	//--- CONSTRUCTOR ----//
	public ParkingLotTicket() {
		
	}
	
	public ParkingLotTicket(String street,Integer numberOfParkingLot,String username,Double moneySpent,long expiringTimestamp ) {
		this.street=street;
		this.numberOfParkingLot=numberOfParkingLot;
		this.username=username;
		this.moneySpent=moneySpent;
		this.expiringTimestamp=expiringTimestamp;
	}
	
	
	//----- ACCESSOR ----//
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		if(username != null && username != "" ) {
			this.username = username;
		}
		else {
			throw new IllegalArgumentException("username is invalid");
			}	
		}
	
	public Double getMoneySpent() {
		return moneySpent;
	}
	
	public void setMoneySpent(Double moneySpent) {
		if(moneySpent != null) {
			this.moneySpent = moneySpent;
		}
		else {
			throw new IllegalArgumentException("moneySpent is invalid");
			}	
	}
	
	
	
	public long getExpiringTimestamp() {
		return expiringTimestamp;
	}
	
	public void setExpiringTimestamp(Long expiringTimestamp) {
		if(numberOfParkingLot != null) {
			this.expiringTimestamp = expiringTimestamp;
		}
		else {
			throw new IllegalArgumentException("expiringTimestamp is invalid");
			}	
	}
	

}
