package com.spm.ParkMe.security.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
	
	@InjectMocks
	private UserDetailsServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	

	@Test
	void throwErrorWhenUserDoesNotExist() {
		Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
		
		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("a@a"));
	}
	
	@Test
	void getUserWhenExistsInDB() {
		Mockito.when(userRepository.findByUsername(Mockito.anyString()))
		.thenReturn(Optional.of(new User("a@a", "a@a", "A", Roles.ROLE_ADMIN)));
		
		assertEquals(UserDetailsImpl.class, userService.loadUserByUsername("a@a").getClass());
	}

}
