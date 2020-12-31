package com.spm.ParkMe.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spm.ParkMe.enums.SensorState;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

public class ParkingLotManager {
	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	
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

}
