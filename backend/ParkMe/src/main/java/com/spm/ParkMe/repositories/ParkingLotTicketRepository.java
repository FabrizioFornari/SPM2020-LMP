package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.ParkingLotTicket;

public interface ParkingLotTicketRepository extends MongoRepository<ParkingLotTicket,String>  {

	List<ParkingLotTicket> findByUsername(String username);
	@Query("{'street':?0, 'numberOfParkingLot': ?1}")
	List<ParkingLotTicket> findByStreetAndNumberOfParkingLot(String street, Integer numberOfParkingLot);
	Boolean existsByUsername(String username);
}


