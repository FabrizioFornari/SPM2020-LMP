package com.spm.ParkMe;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.UserRepository;






@SpringBootApplication
public class ParkMeApplication implements CommandLineRunner {


	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DriverInfoRepository driverInfoRepository;
	
	@Autowired
	private HandicapPermitsRequestsRepository handicapRequestsRepository;
	
	@Autowired
	PasswordEncoder encoder;
	

	public static void main(String[] args) {
		SpringApplication.run(ParkMeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		this.populateDB();
	}

	private void populateDB() {
		User[] users = null;
		users = new User[] { new User("cret@park.it", "Manuel", "Cretone", "ZZZZZZ10A01A000Z", "+39 333 3333333", "cret@park.it", encoder.encode("Cret"), Roles.ROLE_VIGILANT),
				new User("rocche@park.it", "Giacomo", "Rocchetti", "ZZZZZZ10A01A000Z", "+39 333 3333333","rocche@park.it",  encoder.encode("Rocche"), Roles.ROLE_DRIVER),
				new User("flash@park.it", "Andrea", "Falaschini", "ZZZZZZ10A01A000Z", "+39 333 3333333","flash@park.it",  encoder.encode("Flash"), Roles.ROLE_PARKING_MANAGER),
				new User("fusaro@turbomondialismo.it","Diego", "Fusaro", "ZZZZZZ10A01A000Z", "+39 333 3333333","fusaro@turbomondialismo.it", encoder.encode("Fusaro"), Roles.ROLE_ADMIN), };
		Driver driver = new Driver("rocche@park.it", "Giacomo", "Rocchetti", "ZZZZZZ10A01A000Z", "+39 333 3333333","rocche@park.it",  encoder.encode("Rocche"), "AA000AA", "car");
		repository.deleteAll();
		for (User user : users) {
			repository.save(user);
		}
		driverInfoRepository.deleteAll();
		driverInfoRepository.save(new DriverInfo(driver));
		handicapRequestsRepository.deleteAll();
	}

}
