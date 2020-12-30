package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.Notification;


public interface NotificationRepository extends MongoRepository<Notification, String>{

	List<Notification> findByUsername(String username);
}
