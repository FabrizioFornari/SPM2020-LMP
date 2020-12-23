package com.spm.ParkMe.controllers;

import static com.spm.ParkMe.constants.EndpointContants.NOTIFICATION_ENDPOINT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.repositories.NotificationRepository;

@RestController
@RequestMapping(NOTIFICATION_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
public class NotificationController {

	@Autowired
	private NotificationRepository notificationRepository;
}
