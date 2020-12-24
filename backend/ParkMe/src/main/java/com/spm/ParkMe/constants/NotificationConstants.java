package com.spm.ParkMe.constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;

public class NotificationConstants {

	
	public static final String TEXT = "";
	public static final String USERNAME = "";
	public static final long TIMESTAMP = 0;
	public static final CategoryNotification CATEGORY_NOTIFICATION  = null ;
	public static final StatusNotification STATUS_NOTIFICATION  = null ;
	

	
	public static final Notification NOTIFICATION_1 = new Notification(TEXT, USERNAME, TIMESTAMP);

}
