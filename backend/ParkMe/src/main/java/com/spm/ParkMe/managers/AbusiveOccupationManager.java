package com.spm.ParkMe.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.ParkingLotRepository;

public class AbusiveOccupationManager {

	
	@Autowired
	NotificationDispatcher notificationDispatcher;
	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	//--------- CONSTRUCTOR --------- //
	public AbusiveOccupationManager() {
		
	}
	
	
	public Status getStatusParkingLot(String street, int numberOfParkingLot)
	{
		List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		ParkingLot parkingLot = parkingLots.get(0);
		return parkingLot.getStatus();
	}
}
