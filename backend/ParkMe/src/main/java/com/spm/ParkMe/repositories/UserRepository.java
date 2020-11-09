package com.spm.ParkMe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByUsername(String username);
	
	Boolean existsByUsername(String username);
}
