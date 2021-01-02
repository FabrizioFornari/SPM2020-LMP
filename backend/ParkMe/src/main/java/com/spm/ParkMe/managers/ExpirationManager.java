package com.spm.ParkMe.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.Notification;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.ParkingLotTicket;
import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.ParkingLotTicketRepository;

@Component
public class ExpirationManager {

	
	@Autowired
	private NotificationDispatcher notificationDispatcher;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	
	@Autowired
	private ParkingLotTicketRepository parkingLotTicketRepository;
	
	
	//--------- CONSTRUCTOR --------- //
		public ExpirationManager() {
			
		}
		
		
		private Status getStatusParkingLot(String street, Integer numberOfParkingLot)
		{
			List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
			if(!parkingLots.isEmpty()) {
				ParkingLot parkingLot = parkingLots.get(0);
				return parkingLot.getStatus();
			}
			else {
				return null;
			}
		}
		
		
		public void sendNotificationToDriverBeforeTicketExpiring(String street, Integer numberOfParkingLot, String username) {
			
			if(!this.getStatusParkingLot(street, numberOfParkingLot).equals(Status.FREE) ) {
				List<ParkingLotTicket> parkingLotTickets = parkingLotTicketRepository.findByUsername(username);
				ParkingLotTicket parkingLotTicket = parkingLotTickets.get(0);
				Notification notification= new Notification("Ticket Expiring", "The ticket is about to expire. If you want to stay longer please refresh the ticket, otherwise leave the parking lot in 5 minutes.", username, System.currentTimeMillis());
				notification.setCategoryNotification(CategoryNotification.DRIVER_EXPIRING_TICKET);
				notificationDispatcher.sendNotificationToUser(username, notification);
			}
			
			
			
			


		}
	
}
