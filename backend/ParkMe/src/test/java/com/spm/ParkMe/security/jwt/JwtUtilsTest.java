package com.spm.ParkMe.security.jwt;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import com.spm.ParkMe.security.services.UserDetailsImpl;

import io.jsonwebtoken.SignatureException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilsTest {

	@Autowired
	JwtUtils jwtUtils;
	
	private UserDetailsImpl principal;
	private UserDetailsImpl principal2;
	private Authentication authentication;
	private Authentication authentication2;
	
	@BeforeEach
	public void setUp() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_DRIVER"));
		principal = new UserDetailsImpl("id", "rocche@park.it", "rocche@park.it",  "Rocche", authorities);
		principal2 = new UserDetailsImpl("id", "cret@park.it", "cret@park.it",  "Cret", authorities);
		authentication = new UsernamePasswordAuthenticationToken(principal, null);
		authentication2 = new UsernamePasswordAuthenticationToken(principal2, null);
	}
	
	@Test
	public void generatingTokenNotNull() {
		assertNotNull(jwtUtils.generateJwtToken(authentication));
	}
	
	@Test
	public void getRightIdentityFromToken() {
		String token = jwtUtils.generateJwtToken(authentication);
		assertEquals(jwtUtils.getUserNameFromJwtToken(token), ((UserDetailsImpl)authentication.getPrincipal()).getUsername());
	}
	
	@Test
	public void tokensFromDifferentUsersAreDifferent() {
		String token1 = jwtUtils.generateJwtToken(authentication);
		String token2 = jwtUtils.generateJwtToken(authentication2);
		assertNotEquals(jwtUtils.getUserNameFromJwtToken(token1), jwtUtils.getUserNameFromJwtToken(token2));
	}
	
	@Test
	public void validateCorrectToken() {
		String token = jwtUtils.generateJwtToken(authentication);
		assertTrue(jwtUtils.validateJwtToken(token));
	}
	
	@Test
	public void doNotValidateInvalidToken() {
		String token = "some.invalid.token";
		assertFalse(jwtUtils.validateJwtToken(token));
	}
}
