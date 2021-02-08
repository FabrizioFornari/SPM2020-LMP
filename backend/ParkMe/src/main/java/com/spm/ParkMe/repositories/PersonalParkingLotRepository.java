package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spm.ParkMe.models.PersonalParkingLot;

public interface PersonalParkingLotRepository extends MongoRepository<PersonalParkingLot, String> {

	public List<PersonalParkingLot> findByStreet(String street);
	
	@Query("{'street':?0, 'numberOfParkingLot': ?1}")
	public List<PersonalParkingLot> findByStreetAndNumberOfParkingLot(String street, int numberOfParkingLot);
	
}
