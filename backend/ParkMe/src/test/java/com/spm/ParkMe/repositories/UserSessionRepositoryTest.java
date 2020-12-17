package com.spm.ParkMe.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spm.ParkMe.models.UserSession;

import static com.spm.ParkMe.constants.UserInfoConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSessionRepositoryTest {

	@Autowired
	UserSessionRepository repository;
	
	private UserSession session = new UserSession(VIGILANT_OBJECT, "sessionID");
	
	@BeforeEach
	public void setUp() {
		repository.deleteAll();
	}
	
	@Test
	public void userSessionGetsLoadedIntoDB() {
		repository.save(session);
		assertEquals(1, repository.count());
		List<UserSession> l1 = repository.findByUsername(VIGILANT_MAIL);
		assertEquals(1, l1.size());
		UserSession s1 = l1.get(0);
		System.out.println(s1);
		UserSession s2 = repository.findBySessionID("sessionID").get(0);
		System.out.println(s2.getSessionID());
		assertEquals(session, s1);
		assertEquals(session, s2);
	}
	
	@Test
	public void userSessionGetsDeletedFromDB() {
		repository.save(session);
		repository.delete(session);
		assertEquals(0, repository.count());
	}
}
