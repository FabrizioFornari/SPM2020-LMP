package com.spm.ParkMe.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.ParkingLotTicket;

public interface ParkingLotTicketRepository extends MongoRepository<ParkingLotTicket,String>  {

	List<ParkingLotTicket> findByUsername(String username);
	Boolean existsByUsername(String username);
}


