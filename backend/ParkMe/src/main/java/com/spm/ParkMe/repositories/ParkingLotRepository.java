package com.spm.ParkMe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.ParkingLot;

public interface ParkingLotRepository extends MongoRepository<ParkingLot, String> {

}
