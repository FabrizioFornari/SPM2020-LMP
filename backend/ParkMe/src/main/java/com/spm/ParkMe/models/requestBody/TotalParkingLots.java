package com.spm.ParkMe.models.requestBody;

import java.util.ArrayList;
import java.util.List;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.PersonalParkingLot;

public class TotalParkingLots {

	private List<ParkingLot> parkingLots;
	private List<PersonalParkingLot> personalParkingLots;
	
	public TotalParkingLots(List<ParkingLot> parkingLots, List<PersonalParkingLot> personalParkingLots) {
		this.parkingLots = new ArrayList<ParkingLot>(parkingLots);
		this.personalParkingLots = new ArrayList<PersonalParkingLot>(personalParkingLots);
	}
	
	public List<ParkingLot> getParkingLots() {
		return parkingLots;
	}
	public void setParkingLots(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
	}
	public List<PersonalParkingLot> getPersonalParkingLots() {
		return personalParkingLots;
	}
	public void setPersonalParkingLots(List<PersonalParkingLot> personalParkingLots) {
		this.personalParkingLots = personalParkingLots;
	}
}
