package com.spm.ParkMe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByUsername(String email);
	
	public List<User> findByRole(Roles role);
	
	Boolean existsByUsername(String username);

}
