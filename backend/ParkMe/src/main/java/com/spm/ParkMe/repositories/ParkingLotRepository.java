package com.spm.ParkMe.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;

public interface ParkingLotRepository extends MongoRepository<ParkingLot, String> {

	
	public Optional<ParkingLot> findByStreet(String street);
}
