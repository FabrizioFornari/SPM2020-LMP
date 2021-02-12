package com.spm.ParkMe.constants;


import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Notification;

public class NotificationConstants {

	public static final String TITLE = "Abusive Occupation Alert";
	public static final String TEXT = "The parkingLot has been already occupied";
	public static final String USERNAME = "rocche@park.it";
	public static final long TIMESTAMP = 1608801132000l;
	public static final CategoryNotification CATEGORY_NOTIFICATION  = null ;
	public static final StatusNotification STATUS_NOTIFICATION  = null ;
	

	
	public static final Notification NOTIFICATION_1 = new Notification(TITLE, TEXT, USERNAME, TIMESTAMP);
	public static final Notification NOTIFICATION_2 = new Notification(TITLE, TEXT, USERNAME, TIMESTAMP);
	
}
