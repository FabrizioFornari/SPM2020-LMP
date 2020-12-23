package com.spm.ParkMe.controllers;


import static com.spm.ParkMe.constants.EndpointContants.*;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.StatusNotification;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.NotificationRepository;

@RestController
@RequestMapping(NOTIFICATION_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

	@Autowired
	private NotificationRepository notificationRepository;
	
	
	
	@PutMapping(path = NOTIFICATION_SET_STATUS, consumes = "application/json")
	public ResponseEntity setNotificationStatus(@NotNull @RequestParam String id, @NotNull @RequestParam StatusNotification statusNotification ) throws IOException {
		Notification notification =  notificationRepository.findById(id).orElseThrow();
		notification.setStatusNotification(statusNotification);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
