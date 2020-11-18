package com.spm.ParkMe.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.spm.ParkMe.models.HandicapPermitsRequest;

public interface HandicapPermitsRequestsRepository extends MongoRepository<HandicapPermitsRequest, String> {

	
	public Optional<HandicapPermitsRequest> findByUsername(String username);
}
