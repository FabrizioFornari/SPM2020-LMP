package com.spm.ParkMe.constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.requestBody.ChangeNotificationStatusInfo;

public class NotificationConstants {

	
	public static final String TEXT = "The parkingLot has been already occupied";
	public static final String USERNAME = "rocche@park.it";
	public static final long TIMESTAMP = 1608801132000l;
	public static final CategoryNotification CATEGORY_NOTIFICATION  = null ;
	public static final StatusNotification STATUS_NOTIFICATION  = null ;
	

	
	public static final Notification NOTIFICATION_1 = new Notification(TEXT, USERNAME, TIMESTAMP);
	
}
