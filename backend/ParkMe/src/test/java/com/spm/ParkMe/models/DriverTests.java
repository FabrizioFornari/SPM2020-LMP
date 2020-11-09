package com.spm.ParkMe.models;
import org.junit.jupiter.api.Test;

public class DriverTests {

	
	Driver driver =new Driver("Manuel");
	@Test
	void GetFirstName() {
		assert(driver.getFirstName() == "Manuel");
	}
}
