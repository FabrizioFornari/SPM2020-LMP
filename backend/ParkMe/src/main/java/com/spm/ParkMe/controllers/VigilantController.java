package com.spm.ParkMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.repositories.ParkingLotRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;

import java.util.List;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(VIGILANT_ENDPOINT)
@CrossOrigin(origins = "*", maxAge=3600)
public class VigilantController {

	
	@Autowired
	ParkingLotRepository parkingLotRepository;
	
	@GetMapping(path=VIGILANT_GET_ALL_PARKINGLOTS, consumes = "application/json")
	@PreAuthorize("hasRole('VIGILANT')")
	public  List<ParkingLot> getAllParkingLots()  {
		return parkingLotRepository.findAll();
	}
	
	@GetMapping(path=VIGILANT_GET_ALL_PARKINGLOTS_FOR_STREET, consumes = "application/json")
	@PreAuthorize("hasRole('VIGILANT')")	
	public  List<ParkingLot> getAllParkingLotsForSpecificStreet(@NotNull @RequestParam String street)  {
		return parkingLotRepository.findByStreet(street);
	}
	

}
