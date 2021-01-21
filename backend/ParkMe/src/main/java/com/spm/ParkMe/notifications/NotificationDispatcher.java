package com.spm.ParkMe.notifications;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotNotification;
import com.spm.ParkMe.models.PersonalParkingLot;
import com.spm.ParkMe.models.PersonalParkingLotNotification;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.UserSession;
import com.spm.ParkMe.repositories.NotificationRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotRepository;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.repositories.UserSessionRepository;

@Service
public class NotificationDispatcher {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	UserSessionRepository userSessionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	@Autowired 
	PersonalParkingLotRepository personalParkingLotRepository;

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
	        notificationRepository.save(notification);
		}
	}
	
	public void sendNotificationToOneVigilant(String street, Integer numberOfParkingLot) {
	
		List<UserSession> vigilantsSessions= userSessionRepository.findAll().stream().filter(session -> session.getUser().getRole().equals(Roles.ROLE_VIGILANT)).collect(Collectors.toList());
		if(!vigilantsSessions.isEmpty()) {
			String sessionID = vigilantsSessions.get(0).getSessionID();
			String username = vigilantsSessions.get(0).getUser().getUsername();
			List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
			ParkingLot parkingLot= parkingLots.get(0);
			Notification notification = new ParkingLotNotification("Abusive Occupation Alert","An abusive occupation has been detected. Please go check "+ street + " "+ numberOfParkingLot,username, System.currentTimeMillis(),parkingLot);
			notification.setCategoryNotification(CategoryNotification.VIGILANT_ABUSIVE_PARKING);
			this.sendNotificationToUser(username, notification);
		}
		else {
			List<User> vigilants = userRepository.findByRole(Roles.ROLE_VIGILANT);
			long timestamp = System.currentTimeMillis();
			for(User vigilant : vigilants) {
				List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
				ParkingLot parkingLot= parkingLots.get(0);
				Notification notification = new ParkingLotNotification("Abusive Occupation Alert","An abusive occupation has been detected. Please go check "+ street + " "+ numberOfParkingLot,vigilant.getUsername(),timestamp,parkingLot);
				notification.setCategoryNotification(CategoryNotification.VIGILANT_ABUSIVE_PARKING);
				notificationRepository.save(notification);
			}
		}
	}
	
	
	public void sendNotificationToOneVigilantWhenTicketExpiring(String street, Integer numberOfParkingLot) {
		

		List<UserSession> vigilantsSessions= userSessionRepository.findAll().stream().filter(session -> session.getUser().getRole().equals(Roles.ROLE_VIGILANT)).collect(Collectors.toList());
		if(!vigilantsSessions.isEmpty()) {
			String sessionID = vigilantsSessions.get(0).getSessionID();
			String username = vigilantsSessions.get(0).getUser().getUsername();
			List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
			ParkingLot parkingLot= parkingLots.get(0);
			ParkingLotNotification notification = new ParkingLotNotification("Ticket Expiring", "The driver has not renewed the reserved parking, ticket expired. Please go check "+ street + " "+ numberOfParkingLot,username, System.currentTimeMillis(), parkingLot);
			notification.setCategoryNotification(CategoryNotification.VIGILANT_EXPIRING_TICKET);
			this.sendNotificationToUser(username, notification);
		}
		else {
			List<User> vigilants = userRepository.findByRole(Roles.ROLE_VIGILANT);
			long timestamp = System.currentTimeMillis();
			for(User vigilant : vigilants) {
				List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
				ParkingLot parkingLot= parkingLots.get(0);
				ParkingLotNotification notification = new ParkingLotNotification("Ticket Expiring", "The driver has not renewed the reserved parking, ticket expired. Please go check "+ street + " "+ numberOfParkingLot,vigilant.getUsername(), System.currentTimeMillis(), parkingLot);
				notification.setCategoryNotification(CategoryNotification.VIGILANT_EXPIRING_TICKET);
				notificationRepository.save(notification);
			}
		}
	}
	
	public void sendNotificationToOneVigilantForAbusivePersonalParkingLot(String street, Integer numberOfParkingLot) {
		List<UserSession> vigilantsSessions= userSessionRepository.findAll().stream().filter(session -> session.getUser().getRole().equals(Roles.ROLE_VIGILANT)).collect(Collectors.toList());
		if(!vigilantsSessions.isEmpty()) {
			String sessionID = vigilantsSessions.get(0).getSessionID();
			String username = vigilantsSessions.get(0).getUser().getUsername();
			List<PersonalParkingLot> personalParkingLots= personalParkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
			PersonalParkingLot personalParkingLot= personalParkingLots.get(0);
			PersonalParkingLotNotification notification = new PersonalParkingLotNotification("Abusive Personal Parking Lot Occupation", "A personal Parking Lot has been abusively occupied. Please go check "+ street + " "+ numberOfParkingLot,username, System.currentTimeMillis(), personalParkingLot);
			notification.setCategoryNotification(CategoryNotification.VIGILANT_ABUSIVE_PERSONAL_PARKINGLOT);
			this.sendNotificationToUser(username, notification);
		}else {
			List<User> vigilants = userRepository.findByRole(Roles.ROLE_VIGILANT);
			long timestamp = System.currentTimeMillis();
			for(User vigilant : vigilants) {
				List<PersonalParkingLot> personalParkingLots= personalParkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
				PersonalParkingLot personalParkingLot= personalParkingLots.get(0);
				PersonalParkingLotNotification notification = new PersonalParkingLotNotification("Ticket Expiring", "The driver has not renewed the reserved parking, ticket expired. Please go check "+ street + " "+ numberOfParkingLot,vigilant.getUsername(), System.currentTimeMillis(), personalParkingLot);
				notification.setCategoryNotification(CategoryNotification.VIGILANT_EXPIRING_TICKET);
				notificationRepository.save(notification);
			}
		}
	}
	
	
	
	public void add(UserSession session)
	{
		List<UserSession> sessions = userSessionRepository.findByUsername(session.getUser().getUsername());
		//if there are other sessions, delete them and save the new one
		for(UserSession s : sessions) {
			userSessionRepository.delete(s);
		}
		userSessionRepository.save(session);
	}
	
	public void remove(String sessionID) {
		List<UserSession> sessions = userSessionRepository.findBySessionID(sessionID);
		for(UserSession s : sessions) {
			userSessionRepository.delete(s);
		}
	}
}
