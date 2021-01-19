package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class PersonalParkingLotSubscription {
	
	@Id private String id;
	@NotNull(message="Username must not be null")
	@NotEmpty(message = "Username must not be empty")
	private String username;
	@NotNull(message="expiration must not be null")
	private long expiration;
	@NotNull(message="numberOfParkingLot must not be null")
	private int numberOfParkingLot;
	@NotNull(message="Street must not be null")
	@NotEmpty(message = "Street must not be empty")
	private String street;
	
	public PersonalParkingLotSubscription(){
		
	}
	
	public PersonalParkingLotSubscription(String username, long expiration, String street, int numberOfParkingLot){
		this.setUsername(username);
		this.setExpiration(expiration);
		this.setStreet(street);
		this.setNumberOfParkingLot(numberOfParkingLot);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username != null) {
			this.username = username;
		}
		else {
			throw new IllegalArgumentException("username is invalid");
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
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		if(street != null) {
			this.street = street;
		}
		else {
			throw new IllegalArgumentException("street is invalid");
			}	
	}
	
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(Long expiration) {
		if(expiration != null) {
			this.expiration = expiration;
		}
		else {
			throw new IllegalArgumentException("expiration is invalid");
			}	
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PersonalParkingLotSubscription subscription = (PersonalParkingLotSubscription) o;
		return(
				subscription.getStreet().equals(this.getStreet()) &&
				((Integer)subscription.getNumberOfParkingLot()).equals((Integer)this.getNumberOfParkingLot())&&
				subscription.getUsername().equals(this.getUsername()) &&
				subscription.getExpiration() == this.getExpiration());
	}
	

}
