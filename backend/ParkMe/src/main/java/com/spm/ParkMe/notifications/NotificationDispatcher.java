package com.spm.ParkMe.notifications;

import java.util.HashSet;
import java.util.Set;

import javax.management.Notification;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.spm.ParkMe.models.MessageResponse;

public class NotificationDispatcher {

	
	private Set<String> listeners = new HashSet<>();
	private SimpMessagingTemplate template;

	public NotificationDispatcher(SimpMessagingTemplate template) {
	    this.template = template;
	}

	
	@Scheduled(fixedDelay = 2000)
	public void dispatch() {
	    for (String listener : listeners) {
	        System.out.println("Sending notification to " + listener);

	        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	        headerAccessor.setSessionId(listener);
	        headerAccessor.setLeaveMutable(true);

	        int value = (int) Math.round(Math.random() * 100d);
	        template.convertAndSendToUser(
	                listener, 
	                "/notification/item",
	                //new Notification(Integer.toString(value)),
	                new MessageResponse("prova: " + Integer.toString(value)),
	                headerAccessor.getMessageHeaders());
	    }
	}
}
