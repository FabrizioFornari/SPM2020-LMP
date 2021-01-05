package com.spm.ParkMe.models;

public class ParkingLotNotification extends Notification {

	
	private ParkingLot parkingLot;
	
	
	public ParkingLotNotification() {
		// TODO Auto-generated constructor stub
	}

	public ParkingLotNotification(String title, String text, String username, long timeStamp, ParkingLot parkingLot) {
		super(title, text, username, timeStamp);
		// TODO Auto-generated constructor stub
		this.parkingLot=parkingLot;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

}
