package com.spm.ParkMe.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@Component
public class ParkingLotManager {
	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	@Autowired
	ParkingLotBookingRepository parkingLotBookingRepository;
	
	public void occupyParkingLot(String street, int numberOfParkingLot) throws Exception{
		try {
			ParkingLot parkingLot = this.getParkingLot(street, numberOfParkingLot);
			if(parkingLot.getStatus().equals(Status.BOOKED)) {
				if(this.parkingLotBookingExists(street, numberOfParkingLot)) {
					throw new Exception("A booking for this parking lot already exists.");
				}
				else {
					ParkingLotBooking booking = this.getParkingLotBooking(street, numberOfParkingLot);
					parkingLotBookingRepository.delete(booking);
					this.setParkingLotStatus(street, numberOfParkingLot, Status.OCCUPIED);
				}
			}
			else {
				throw new Exception("The parking lot must be BOOKED in order to occupy it");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void setParkingLotStatus(String street, int numberOfParkingLot, Status status) {
		try {
			ParkingLot parkingLot = this.getParkingLot(street, numberOfParkingLot);
			parkingLot.setStatus(status);
			parkingLotRepository.save(parkingLot);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void setParkingLotSensor(String street, int numberOfParkingLot, SensorState sensorState) {
		try {
			ParkingLot parkingLot = this.getParkingLot(street, numberOfParkingLot);
			parkingLot.setSensorState(sensorState);
			parkingLotRepository.save(parkingLot);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private ParkingLot getParkingLot(String street, int numberOfParkingLot) throws Exception {
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		if(parkingLots.size() == 1) {
			return parkingLots.get(0);
		}
		if(parkingLots.size() > 1) {
			throw new Exception("There is more than one parking lot corresponding to the same street and number: " + street + " #" + numberOfParkingLot);
		}
		else {
			throw new Exception("There is no parking lot corresponding to " + street + " #" + numberOfParkingLot);
		}
	}
	
	private ParkingLotBooking getParkingLotBooking(String street, int numberOfParkingLot) throws Exception {
		List<ParkingLotBooking> parkingLotBookings = parkingLotBookingRepository.findByStreetAndNumberOfParkingLotBooking(street, numberOfParkingLot);
		if(parkingLotBookings.size() == 1) {
			return parkingLotBookings.get(0);
		}
		if(parkingLotBookings.size() > 1) {
			throw new Exception("There is more than one booking corresponding to the same street and number: " + street + " #" + numberOfParkingLot);
		}
		else {
			throw new Exception("There is no booking corresponding to " + street + " #" + numberOfParkingLot);
		}
	}
	
	private boolean parkingLotBookingExists(String street, int numberOfParkingLot) {
		List<ParkingLotBooking> parkingLotBookings = parkingLotBookingRepository.findByStreetAndNumberOfParkingLotBooking(street, numberOfParkingLot);
		return parkingLotBookings.size() > 0;
	}

	private boolean parkingLotBookingExists(String username) {
		List<ParkingLotBooking> parkingLotBookings = parkingLotBookingRepository.findByUsername(username);
		return parkingLotBookings.size() > 0;
	}
}
