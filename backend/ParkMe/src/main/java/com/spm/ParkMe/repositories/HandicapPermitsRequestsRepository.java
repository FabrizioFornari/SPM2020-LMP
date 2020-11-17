package com.spm.ParkMe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.HandicapPermitsRequest;

public interface HandicapPermitsRequestsRepository extends MongoRepository<HandicapPermitsRequest, String> {

}
