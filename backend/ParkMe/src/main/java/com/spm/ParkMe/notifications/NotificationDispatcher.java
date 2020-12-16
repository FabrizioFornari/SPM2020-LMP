package com.spm.ParkMe.notifications;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.Notification;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.UserSession;

@Service
public class NotificationDispatcher {

	
	private Set<UserSession> listeners = new HashSet<>();
	private SimpMessagingTemplate template;

	public NotificationDispatcher(SimpMessagingTemplate template) {
	    this.template = template;
	}

	
	@Scheduled(fixedDelay = 2000)
	public void dispatch() {
	    for (UserSession listener : listeners) {
	        System.out.println("Sending notification to " + listener.getUser().getUsername());

	        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	        headerAccessor.setSessionId(listener.getSessionID());
	        headerAccessor.setLeaveMutable(true);

	        int value = (int) Math.round(Math.random() * 100d);
	        template.convertAndSendToUser(
	                listener.getSessionID(), 
	                "/notification/item",
	                //new Notification(Integer.toString(value)),
	                new MessageResponse("prova: " + Integer.toString(value)),
	                headerAccessor.getMessageHeaders());
	    }
	}
	
	
	public void add(UserSession session)
	{
		this.listeners.add(session);
	}
	
	public void remove(String sessionID) {
		List<UserSession> sessions = this.listeners.stream().filter(l -> l.getSessionID().equals(sessionID)).collect(Collectors.toList());
		if(!sessions.isEmpty()) {
			this.listeners.remove(sessions.get(0));
		}
	}
}
