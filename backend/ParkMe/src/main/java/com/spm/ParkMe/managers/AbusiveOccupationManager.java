package com.spm.ParkMe.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;

public class AbusiveOccupationManager {

	
	@Autowired
	NotificationDispatcher notificationDispatcher;
	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	@Autowired
	ParkingLotBookingRepository parkingLotBookingRepository;
	
	//--------- CONSTRUCTOR --------- //
	public AbusiveOccupationManager() {
		
	}
	
	
	public Status getStatusParkingLot(String street, int numberOfParkingLot)
	{
		List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		ParkingLot parkingLot = parkingLots.get(0);
		return parkingLot.getStatus();
	}
	
	public void sendDriverNotification(String street, int numberOfParkingLot) {
		if(getStatusParkingLot( street,  numberOfParkingLot).equals(Status.OCCUPIED)) {
			List<ParkingLotBooking> parkingLotsBooking= parkingLotBookingRepository.findByStreetAndNumberOfParkingLotBooking(street, numberOfParkingLot);
			
			if(!parkingLotsBooking.isEmpty()) {
				ParkingLotBooking parkingLotBooking = parkingLotsBooking.get(0);
				String username = parkingLotBooking.getUsername();
				Notification notification = new Notification("",username, System.currentTimeMillis());
				notification.setCategoryNotification(CategoryNotification.PARKING);
				notificationDispatcher.sendNotificationToUser(username, notification);
			}
		}
	}
}
