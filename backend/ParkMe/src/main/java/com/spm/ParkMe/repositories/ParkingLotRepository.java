package com.spm.ParkMe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;

public interface ParkingLotRepository extends MongoRepository<ParkingLot, String> {

	
	public List<ParkingLot> findByStreet(String street);
	
	@Query("{'street':?0, 'numberOfParkingLot': ?1}")
	public List<ParkingLot> findByStreetAndNumberOfParkingLot(String street, int numberOfParkingLot);
	
	public List<ParkingLot> findByUsername(String username);
}
