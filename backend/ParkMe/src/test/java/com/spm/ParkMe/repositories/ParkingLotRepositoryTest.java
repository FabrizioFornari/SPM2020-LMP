package com.spm.ParkMe.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;
import static com.spm.ParkMe.constants.ParkingLotCostants.*;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.User;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotRepositoryTest {
	@Autowired 
	ParkingLotRepository parkingLotRepository;

	private ParkingLot testParkingLot = new ParkingLot(VALID_STREET, VALID_NUMBROFPARKINGLOT, VALID_ISHANDICAPPARKINGLOT, VALID_PRICEPERHOURS, CAR, VALID_COORDINATES);

	@BeforeEach
	public void setUp() {
		parkingLotRepository.deleteAll();
		
	}
	
	@Test
	void addingAParkingLotIntoTheDBIncreasesCount() {
		parkingLotRepository.save(testParkingLot);
		assertEquals(1, parkingLotRepository.count());
	}
	
	@Test
	void findByStreetReturnsAParkingLotWhenExists() {
		parkingLotRepository.save(testParkingLot);
		assertEquals(testParkingLot, parkingLotRepository.findByStreet(VALID_STREET).get(0));
	}
	
	@Test
	void deleteAParkingLotIntoTheDBIndecreasesCount() {
		parkingLotRepository.save(testParkingLot);
		parkingLotRepository.delete(testParkingLot);
		assertEquals(0, parkingLotRepository.count());
	}
	
	@Test 
	void findByStreetAndNumberOfParkingLotWhenParkingLotExist() {
		parkingLotRepository.save(testParkingLot);
		assertEquals(testParkingLot, parkingLotRepository.findByStreetAndNumberOfParkingLot(VALID_STREET,VALID_NUMBROFPARKINGLOT).get(0));
	}
	
	@Test
	void findCompatibleFreeParkingLotReturnsParkingLots(){
		parkingLotRepository.save(testParkingLot);                             
		assertEquals(testParkingLot, parkingLotRepository.findCompatibleFreeParkingLots(false, CAR).get(0));
	}
	
	@Test
	void findCompatibleFreeParkingLotReturnsEmpty(){
		parkingLotRepository.save(testParkingLot);                             
		assertEquals(0, parkingLotRepository.findCompatibleFreeParkingLots(true, CAR).size());
	}

}
