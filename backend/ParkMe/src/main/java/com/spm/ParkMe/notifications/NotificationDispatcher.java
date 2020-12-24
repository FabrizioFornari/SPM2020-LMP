package com.spm.ParkMe.notifications;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.UserSession;
import com.spm.ParkMe.repositories.NotificationRepository;
import com.spm.ParkMe.repositories.UserSessionRepository;

@Service
public class NotificationDispatcher {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	UserSessionRepository userSessionRepository;

	
	private Set<UserSession> listeners = new HashSet<>();
	private SimpMessagingTemplate template;

	public NotificationDispatcher(SimpMessagingTemplate template) {
	    this.template = template;
	}
	
	public void sendNotificationToUser(String username, Notification notification) {
		List<UserSession> sessions = userSessionRepository.findByUsername(username);
		if(!sessions.isEmpty()) {
			String sessionID = sessions.get(0).getSessionID();
			SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	        headerAccessor.setSessionId(sessionID);
	        headerAccessor.setLeaveMutable(true);
	        template.convertAndSendToUser(
	                sessionID, 
	                "/notification/item",
	                notification,
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
