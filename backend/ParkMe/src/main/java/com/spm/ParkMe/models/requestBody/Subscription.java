package com.spm.ParkMe.models.requestBody;

import com.spm.ParkMe.models.PersonalParkingLot;

public class Subscription {

	private PersonalParkingLot personalParkingLot;
	private int months;
	
	public Subscription() {
		// TODO Auto-generated constructor stub
	}
	public Subscription(PersonalParkingLot personalParkingLot, int months)
	{
		this.setMonths(months);
		this.setPersonalParkingLot(personalParkingLot);
	}
	
	
	public PersonalParkingLot getPersonalParkingLot() {
		return personalParkingLot;
	}
	public void setPersonalParkingLot(PersonalParkingLot personalParkingLot) {
		this.personalParkingLot = personalParkingLot;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}

}
