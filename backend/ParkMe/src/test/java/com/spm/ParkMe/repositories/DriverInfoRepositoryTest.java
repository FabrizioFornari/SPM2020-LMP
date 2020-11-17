package com.spm.ParkMe.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static com.spm.ParkMe.constants.UserInfoConstants.*;

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
	
	private DriverInfo testDriverInfo = new DriverInfo(DRIVER_OBJECT);
	
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
		assertEquals(Optional.empty(), driverInfoRepository.findByUsername("random"));
	}
	
	@Test
	void findByUsernameReturnsADriverInfoWhenExists() {
		driverInfoRepository.save(testDriverInfo);
		assertEquals(Optional.of(testDriverInfo), driverInfoRepository.findByUsername(DRIVER_MAIL));
	}

}
