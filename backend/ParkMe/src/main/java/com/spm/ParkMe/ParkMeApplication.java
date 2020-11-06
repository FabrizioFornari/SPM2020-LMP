package com.spm.ParkMe;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spm.ParkMe.encryption.Encryptor;
import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ParkMeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ParkMeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.populateDB();
	}

	private void populateDB() {

		User[] users = null;
		users = new User[] { new User("Cret", Encryptor.encryptPassword("Cret"), Roles.VIGILANT),
				new User("Rocche", Encryptor.encryptPassword("Rocche"), Roles.DRIVER),
				new User("Flash", Encryptor.encryptPassword("Flash"), Roles.PARKING_MANAGER),
				new User("Fusaro", Encryptor.encryptPassword("Fusaro"), Roles.ADMIN), };
		repository.deleteAll();
		for (User user : users) {
			repository.save(user);
		}
	}
}
