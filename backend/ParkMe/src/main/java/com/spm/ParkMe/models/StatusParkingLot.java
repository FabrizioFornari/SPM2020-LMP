package com.spm.ParkMe.models;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.enums.Status;

public class StatusParkingLot {

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
		this.parkingLot = parkingLot;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
