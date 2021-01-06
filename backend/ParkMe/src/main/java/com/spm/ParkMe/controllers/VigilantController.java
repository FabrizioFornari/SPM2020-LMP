package com.spm.ParkMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.requestBody.StreetInfo;
import com.spm.ParkMe.repositories.ParkingLotRepository;

import static com.spm.ParkMe.constants.EndpointContants.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
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
	

	@GetMapping(path=VIGILANT_GET_PARKINGLOT, consumes = "application/json")
	@PreAuthorize("hasRole('VIGILANT')")	
	public ResponseEntity getParkingLot(@NotNull @RequestParam String street, @NotNull @RequestParam Integer numberOfParkingLot)  {
		List<ParkingLot> parkingLots = parkingLotRepository.findByStreetAndNumberOfParkingLot(street, numberOfParkingLot);
		if(!parkingLots.isEmpty()) {
			return ResponseEntity.ok(parkingLots.get(0));
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = VIGILANT_GET_ALL_STREET_NAME)
	@PreAuthorize("hasRole('VIGILANT')")
	public ResponseEntity<List<StreetInfo>> getAllStreetInfoName() {
		List<StreetInfo> infos = parkingLotRepository.findAll().stream().map(lot -> lot.getStreet()).distinct()
				.map(street -> new StreetInfo(parkingLotRepository.findByStreet(street).get(0).getStreet(),
						parkingLotRepository.findByStreet(street).get(0).getCoordinates()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(infos);
	}
	
	

	@PutMapping(path = VIGILANT_SET_PARKINGLOT_STATUS_DISABLED, consumes = "application/json")
	@PreAuthorize("hasRole('VIGILANT')")
	public ResponseEntity setStatusParkingLotDisabled(@NotNull @RequestBody ParkingLot parkingLot ) throws IOException {
		
		List<ParkingLot> parks = parkingLotRepository.findByStreetAndNumberOfParkingLot(parkingLot.getStreet(),parkingLot.getNumberOfParkingLot());
		if(!parks.isEmpty()) {
			ParkingLot park = parks.get(0);
			park.setStatus(Status.DISABLED);
			parkingLotRepository.save(park);
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
