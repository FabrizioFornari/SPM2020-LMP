package com.spm.ParkMe;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spm.ParkMe.encryption.Encryptor;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ParkMeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;
	
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
		users = new User[] { new User("Cret", "cret@park.it", encoder.encode("Cret"), Roles.VIGILANT),
				new User("Rocche", "rocche@park.it",  encoder.encode("Rocche"), Roles.DRIVER),
				new User("Flash", "flash@park.it",  encoder.encode("Flash"), Roles.PARKING_MANAGER),
				new User("Fusaro", "fusaro@turbomondialismo.it", encoder.encode("Fusaro"), Roles.ADMIN), };
		repository.deleteAll();
		for (User user : users) {
			repository.save(user);
		}
	}
}
