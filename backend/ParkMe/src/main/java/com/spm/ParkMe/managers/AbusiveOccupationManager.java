package com.spm.ParkMe.managers;

import org.springframework.beans.factory.annotation.Autowired;

import com.spm.ParkMe.notifications.NotificationDispatcher;

public class AbusiveOccupationManager {

	
	@Autowired
	NotificationDispatcher notificationDispatcher;
	
	
	//--------- CONSTRUCTOR --------- //
	public AbusiveOccupationManager() {
		
	}
}
