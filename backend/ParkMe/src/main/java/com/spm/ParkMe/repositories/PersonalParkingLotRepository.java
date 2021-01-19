package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.PersonalParkingLot;

public interface PersonalParkingLotRepository extends MongoRepository<PersonalParkingLot, String> {

	public List<PersonalParkingLot>  findByUsername(String username);
}
