package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;

public interface ParkingLotBookingRepository  extends MongoRepository<ParkingLotBooking, String> {
	
	List<ParkingLotBooking> findByUsername(String username);
	
	@Query("{'street':?0, 'numberOfParkingLot': ?1}")
	public List<ParkingLotBooking> findByStreetAndNumberOfParkingLotBooking(String street, int numberOfParkingLot);
	
	Boolean existsByUsername(String username);
}
