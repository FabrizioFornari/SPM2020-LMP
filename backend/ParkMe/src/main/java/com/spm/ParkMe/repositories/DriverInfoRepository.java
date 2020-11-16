package com.spm.ParkMe.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.DriverInfo;

public interface DriverInfoRepository extends MongoRepository<DriverInfo, String> {

	public Optional<DriverInfo> findByUsername(String username);
}
