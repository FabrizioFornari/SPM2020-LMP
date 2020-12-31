package com.spm.ParkMe.managers;

import org.springframework.beans.factory.annotation.Autowired;

import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;

public class ExpirationManager {

	
	@Autowired
	private NotificationDispatcher notificationDispatcher;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	
	
	
	//--------- CONSTRUCTOR --------- //
		public ExpirationManager() {
			
		}
	
}
