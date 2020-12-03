package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.ParkingLotBooking;

public interface ParkingLotBookingRepository  extends MongoRepository<ParkingLotBooking, String> {
	
	List<ParkingLotBooking> findByUsername(String username);
	
	Boolean existsByUsername(String username);
}
