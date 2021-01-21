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
import com.spm.ParkMe.models.PersonalParkingLot;
import com.spm.ParkMe.models.PersonalParkingLotSubscription;
import com.spm.ParkMe.notifications.NotificationDispatcher;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotSubscriptionRepository;

@Component
public class AbusiveOccupationManager {

	
	@Autowired
	private NotificationDispatcher notificationDispatcher;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	@Autowired
	private PersonalParkingLotSubscriptionRepository personalParkingLotSubscriptionRepository;
	
	private boolean solved;
	
	//--------- CONSTRUCTOR --------- //
	public AbusiveOccupationManager() {
		this.setSolved(false);
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
	
	private SensorState getParkingLotSensorState(String street, Integer numberOfParkingLot) {
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		if(!parkingLots.isEmpty()) {
			ParkingLot parkingLot = parkingLots.get(0);
			return parkingLot.getSensorState();
		}
		else {
			return null;
		}
	}
	
	
	public void sendNotificationToDriver(String street, Integer numberOfParkingLot) {
		this.solved=false;
		if(this.getStatusParkingLot(street, numberOfParkingLot).equals(Status.OCCUPIED) || this.getParkingLotSensorState(street, numberOfParkingLot).equals(SensorState.OFF)) {
			this.solved = true;
			return;
		}
		if(this.getStatusParkingLot(street, numberOfParkingLot).equals(Status.BOOKED)) {
			List<ParkingLotBooking> parkingLotsBooking= parkingLotBookingRepository.findByStreetAndNumberOfParkingLotBooking(street, numberOfParkingLot);
			if(!parkingLotsBooking.isEmpty()) {
				ParkingLotBooking parkingLotBooking = parkingLotsBooking.get(0);
				String username = parkingLotBooking.getUsername();
				Notification notification = new Notification("Abusive Occupation Alert", "The parking lot that you booked (" + street + " - #" + numberOfParkingLot + ") has been occupied. Is that you?",username, System.currentTimeMillis());
				notification.setCategoryNotification(CategoryNotification.DRIVER_ABUSIVE_PARKING);
				notificationDispatcher.sendNotificationToUser(username, notification);
			}
		}
	}


	public void  sendNotificationToVigilant(String street, Integer numberOfParkingLot) {
		if(!this.getStatusParkingLot(street, numberOfParkingLot).equals(Status.OCCUPIED) && this.getParkingLotSensorState(street, numberOfParkingLot).equals(SensorState.ON))
		{
			notificationDispatcher.sendNotificationToOneVigilant(street, numberOfParkingLot);
			List<ParkingLot> parkingLots= parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
			ParkingLot parkingLot= parkingLots.get(0);
			parkingLot.setStatus(Status.FREE);
			parkingLotRepository.save(parkingLot);
			List<ParkingLotBooking> bookings = parkingLotBookingRepository.findByStreetAndNumberOfParkingLotBooking(street, numberOfParkingLot);
			if(!bookings.isEmpty()) {
				ParkingLotBooking booking = bookings.get(0);
				parkingLotBookingRepository.delete(booking);
			}
		}else
		{
			this.solved=true;
			return;
		}
		
	}
	
	public void sendNotificationToVigilantForAbusivePersonalParkingLot(String street, Integer numberOfParkingLot) {
		notificationDispatcher.sendNotificationToOneVigilantForAbusivePersonalParkingLot(street, numberOfParkingLot);
	}
	
	
	
	public boolean isSolved() {
		return solved;
	}


	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
}
