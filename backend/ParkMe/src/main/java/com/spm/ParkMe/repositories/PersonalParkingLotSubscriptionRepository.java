package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spm.ParkMe.models.PersonalParkingLotSubscription;

public interface PersonalParkingLotSubscriptionRepository extends MongoRepository<PersonalParkingLotSubscription, String> {

	public List<PersonalParkingLotSubscription>  findByUsername(String username);
	@Query("{'street':?0, 'numberOfParkingLot': ?1}")
	public List<PersonalParkingLotSubscription> findByStreetAndNumberOfParkingLot(String street, int numberOfParkingLot);
}
