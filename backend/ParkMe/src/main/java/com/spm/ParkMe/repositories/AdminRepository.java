package com.spm.ParkMe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.Admin;


public interface AdminRepository extends MongoRepository<Admin, Admin> {
	
	
}
