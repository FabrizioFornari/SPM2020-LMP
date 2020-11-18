package com.spm.ParkMe.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spm.ParkMe.models.HandicapPermitsRequest;

import static com.spm.ParkMe.constants.UserInfoConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HandicapPermitsRequestsRepositoryTest {

	@Autowired
	HandicapPermitsRequestsRepository handicapRequestsrepository;
	
	private HandicapPermitsRequest request1;
	private HandicapPermitsRequest request2;
	
	@BeforeEach
	public void setUp() {
		handicapRequestsrepository.deleteAll();
		request1 = new HandicapPermitsRequest(VALID_EMAIL, 123456789, false, false);
		request2 = new HandicapPermitsRequest(ADMIN_MAIL, 1234567890, false, true);
	}
	
	@Test
	@DisplayName("Requests created correctly")
	public void requests() {
		assertEquals(request1.getUsername(), VALID_EMAIL);
		assertEquals(request1.getTimestamp(), 123456789);
		assertEquals(request2.getUsername(), ADMIN_MAIL);
		assertEquals(request2.getTimestamp(), 1234567890);
	}
	
	@Test
	void addingARequestIntoTheDBIncreasesCount() {
		handicapRequestsrepository.save(request1);
		assertEquals(1, handicapRequestsrepository.count());
	}

	@Test
	void findAllReturnsEmptyWhenDBIsEmpty() {
		assertEquals(0, handicapRequestsrepository.findAll().size());
	}
	
	@Test
	void findAllReturnsListOfRequestsWhenDBIsNotEmpty() {
		handicapRequestsrepository.save(request1);
		handicapRequestsrepository.save(request2);
		List<HandicapPermitsRequest> list = handicapRequestsrepository.findAll();
		assertEquals(2, list.size());
		assertTrue(list.contains(request1));
		assertTrue(list.contains(request2));
	}
}
