package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parkingLotBookings")
public class ParkingLotBooking {

	@Id private String id;
	@NotNull(message="Street must not be null")
	@NotEmpty(message = "Street must not be empty")
	private String street;
	@NotNull(message="numberOfParkingLot must not be null")
	private Integer numberOfParkingLot;
	@NotNull(message="username must not be null")
	private String username;
	@NotNull(message="Timestamp must not be null")
	private long timestamp;
	@NotNull(message="Coordinates must not be null")
	private Coordinates coordinates;
	
	public ParkingLotBooking() {}
	
	public ParkingLotBooking(String street, Integer numberOfParkingLot, String username, long timestamp, Coordinates coordinates) {
		this.setStreet(street);
		this.setNumberOfParkingLot(numberOfParkingLot);
		this.setUsername(username);
		this.setTimestamp(timestamp);
		this.setCoordinates(coordinates);
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		if(numberOfParkingLot != null) {
			this.username = username;
		}
		else {
			throw new IllegalArgumentException("username is invalid");
			}	
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		if(numberOfParkingLot != null) {
			this.timestamp = timestamp;
		}
		else {
			throw new IllegalArgumentException("timestamp is invalid");
			}	
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ParkingLotBooking booking = (ParkingLotBooking) o;
		return(booking.getStreet().equals(this.getStreet()) &&
				((Integer)booking.getNumberOfParkingLot()).equals((Integer)this.getNumberOfParkingLot()) &&
				booking.getUsername().equals(this.getUsername()) &&
				((Long)booking.getTimestamp()).equals((Long)this.getTimestamp()) &&
				booking.getCoordinates().equals(this.getCoordinates()));
		
	}
}
