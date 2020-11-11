package com.spm.ParkMe.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
		assertEquals(userRepository.count(), 1);
	}

	@Test
	void findByUsernameReturnsEmptyWhenDBIsEmpty() {
		assertEquals(userRepository.findByUsername(""), Optional.empty());
	}
	
	@Test
	void findByUsernameReturnsEmptyWhenTheUsernameDoesNotExist() {
		userRepository.save(testUser);
		assertEquals(userRepository.findByUsername("b@b"), Optional.empty());
	}

}
