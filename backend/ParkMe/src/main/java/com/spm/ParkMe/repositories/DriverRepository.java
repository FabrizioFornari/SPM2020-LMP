package com.spm.ParkMe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.Driver;

public interface DriverRepository extends MongoRepository<Driver, Driver> {
	
	
}
