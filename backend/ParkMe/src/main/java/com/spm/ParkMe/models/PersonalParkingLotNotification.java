package com.spm.ParkMe.models;

public class PersonalParkingLotNotification extends Notification{


	
	private PersonalParkingLot personalParkingLot;
	
	
	public PersonalParkingLotNotification() {
		// TODO Auto-generated constructor stub
	}

	public PersonalParkingLotNotification(String title, String text, String username, long timeStamp, PersonalParkingLot personalParkingLot) {
		super(title, text, username, timeStamp);
		// TODO Auto-generated constructor stub
		this.personalParkingLot=personalParkingLot;
	}

	public PersonalParkingLot getParkingLot() {
		return personalParkingLot;
	}

	public void setParkingLot(PersonalParkingLot personalParkingLot) {
		this.personalParkingLot = personalParkingLot;
	}
}
