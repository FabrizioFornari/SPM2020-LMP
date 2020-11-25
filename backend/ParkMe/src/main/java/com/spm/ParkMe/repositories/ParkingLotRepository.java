package com.spm.ParkMe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;

public interface ParkingLotRepository extends MongoRepository<ParkingLot, String> {

	
	public List<ParkingLot> findByStreet(String street);
}
