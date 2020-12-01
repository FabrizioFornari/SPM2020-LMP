package com.spm.ParkMe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.StatusParkingLot;

public interface StatusParkingLotRepository extends MongoRepository<StatusParkingLot, String> {

}
