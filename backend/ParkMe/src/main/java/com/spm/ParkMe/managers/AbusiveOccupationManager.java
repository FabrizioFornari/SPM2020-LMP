package com.spm.ParkMe.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@Component
public class AbusiveOccupationManager {

	
	@Autowired
	private NotificationDispatcher notificationDispatcher;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	
	//--------- CONSTRUCTOR --------- //
	public AbusiveOccupationManager() {
		
	}
	
	
	private Status getStatusParkingLot(String street, Integer numberOfParkingLot)
	{
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		if(!parkingLots.isEmpty()) {
			ParkingLot parkingLot = parkingLots.get(0);
			return parkingLot.getStatus();
		}
		else {
			return null;
		}
	}
	
	public void sendDriverNotification(String street, Integer numberOfParkingLot) {
		if(this.getStatusParkingLot(street, numberOfParkingLot).equals(Status.BOOKED)) {
			List<ParkingLotBooking> parkingLotsBooking= parkingLotBookingRepository.findByStreetAndNumberOfParkingLotBooking(street, numberOfParkingLot);
			if(!parkingLotsBooking.isEmpty()) {
				ParkingLotBooking parkingLotBooking = parkingLotsBooking.get(0);
				String username = parkingLotBooking.getUsername();
				Notification notification = new Notification("Abusive Occupation Alert", "The parking lot that you booked (" + street + " - #" + numberOfParkingLot + ") has been occupied. Is that you?",username, System.currentTimeMillis());
				notification.setCategoryNotification(CategoryNotification.PARKING);
				notificationDispatcher.sendNotificationToUser(username, notification);
			}
		}
	}
}
