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
import com.spm.ParkMe.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	private User testUser = new User("a@a", "a@a", "A", Roles.ROLE_ADMIN);
	
	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
	}
	
	@Test
	void addingAUserIntoTheDBIncreasesCount() {
		userRepository.save(testUser);
		assertEquals(1, userRepository.count());
	}

	@Test
	void findByUsernameReturnsEmptyWhenDBIsEmpty() {
		assertEquals(Optional.empty(), userRepository.findByUsername(""));
	}
	
	@Test
	void findByUsernameReturnsEmptyWhenTheUsernameDoesNotExist() {
		userRepository.save(testUser);
		assertEquals(Optional.empty(), userRepository.findByUsername("b@b"));
	}
	
	@Test
	void findByUsernameReturnsAUserWhenExists() {
		userRepository.save(testUser);
		assertEquals(Optional.of(testUser), userRepository.findByUsername("a@a"));
	}

}
