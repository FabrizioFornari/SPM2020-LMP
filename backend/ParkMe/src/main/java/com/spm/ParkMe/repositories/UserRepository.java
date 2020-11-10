package com.spm.ParkMe.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByEmail(String email);
	public Optional<User> findByUsername(String email);
	
	Boolean existsByEmail(String email);
	Boolean existsByUsername(String username);

}
