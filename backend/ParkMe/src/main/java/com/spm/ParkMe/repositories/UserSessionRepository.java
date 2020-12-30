package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spm.ParkMe.models.UserSession;

public interface UserSessionRepository extends MongoRepository<UserSession, String>{
	
	@Query("{'user.username':?0}")
	List<UserSession> findByUsername(String username);
	
	List<UserSession> findBySessionID(String sessionID);
}
