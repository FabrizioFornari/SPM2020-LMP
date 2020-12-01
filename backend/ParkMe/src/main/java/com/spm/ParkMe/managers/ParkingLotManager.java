package com.spm.ParkMe.managers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

@Component
public class ParkingLotManager {
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	ParkingLotManager(){}
	
	public void insertParkingLot(ParkingLot parkingLot) {
		parkingLotRepository.save(parkingLot);
	}
	
	public void deleteParkingLot(ParkingLot parkingLot) {
		parkingLotRepository.delete(parkingLot);
	}
	
	public boolean parkingLotAlreadyExists(ParkingLot parkingLot) {
		if(this.getParkingLotFromStreetAndNumber(parkingLot.getStreet(), parkingLot.getNumberOfParkingLot()) != null) {
			return true;
		}
		return false;
	}
	
	public ParkingLot getParkingLotFromStreetAndNumber(String street, int number) {
		//first get the parking lots for the street
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreet(street);
		//check if there is already a parking lot with specified number
		List<ParkingLot> parkingLotsWithSameNumber = parkingLots.stream().filter(lot -> lot.getNumberOfParkingLot() == number).collect(Collectors.toList());
		if(parkingLotsWithSameNumber.isEmpty()) {
			return null;
		}
		else {
			return parkingLotsWithSameNumber.get(0);
		}
	}
}
