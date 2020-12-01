package com.spm.ParkMe.models;

import javax.validation.constraints.NotNull;

import com.spm.ParkMe.constants.RegexConstants;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.enums.Status;

public class StatusParkingLot {

	@NotNull(message="Password must not be null")
	private ParkingLot parkingLot;
	private Status status; 
	
	
	//----CONSTRUCTOR ----//
	public StatusParkingLot() {
		
	}
	
	public StatusParkingLot(ParkingLot parkingLot, Status status) {
		this.parkingLot=parkingLot;
		this.status=status;
	}
	
	//---- ACCESSOR ----//
	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		if(parkingLot != null ) {
			this.parkingLot = parkingLot;
		}
		else {
			throw new IllegalArgumentException("parkingLot is invalid");
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
