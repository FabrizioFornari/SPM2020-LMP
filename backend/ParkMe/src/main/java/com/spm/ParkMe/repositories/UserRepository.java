package com.spm.ParkMe.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
}
