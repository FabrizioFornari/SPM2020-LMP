package com.spm.ParkMe.repositories;

import static org.junit.jupiter.api.Assertions.*;

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
import com.spm.ParkMe.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverInfoRepositoryTest {
	
	@Autowired
	DriverInfoRepository driverInfoRepository;
	
	private DriverInfo testDriverInfo = new DriverInfo(new Driver("rocche@park.it", "Giacomo", "Rocchetti", "ZZZZZZ10A01A000Z", "+39 333 3333333","rocche@park.it",  "Rocche", "AA000AA", "car"));
	
	@BeforeEach
	public void setUp() {
		driverInfoRepository.deleteAll();
	}
	
	@Test
	void addingADriverInfoIntoTheDBIncreasesCount() {
		driverInfoRepository.save(testDriverInfo);
		assertEquals(1, driverInfoRepository.count());
	}

	@Test
	void findByUsernameReturnsEmptyWhenDBIsEmpty() {
		assertEquals(Optional.empty(), driverInfoRepository.findByUsername(""));
	}
	
	@Test
	void findByUsernameReturnsEmptyWhenTheDriverInfoDoesNotExist() {
		driverInfoRepository.save(testDriverInfo);
		assertEquals(Optional.empty(), driverInfoRepository.findByUsername("b@b"));
	}
	
	@Test
	void findByUsernameReturnsADriverInfoWhenExists() {
		driverInfoRepository.save(testDriverInfo);
		assertEquals(Optional.of(testDriverInfo), driverInfoRepository.findByUsername("rocche@park.it"));
	}

}
