package com.spm.ParkMe.models.requestBody;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Coordinates;

public class ChangeNotificationStatusInfo {

	private String id;
	private StatusNotification statusNotification;
	
	//------------ Constructor -----//
	public ChangeNotificationStatusInfo() {
		
	}
	
	public ChangeNotificationStatusInfo(String id ,StatusNotification statusNotification){
		this.id=id;
		this.setStatusNotification(statusNotification);
	}

	public String getId() {
		return id;
	}


	public StatusNotification getStatusNotification() {
		return statusNotification;
	}

	public void setStatusNotification(StatusNotification statusNotification) {
		this.statusNotification = statusNotification;
	}
}
	
	

