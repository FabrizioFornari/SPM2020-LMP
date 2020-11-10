package com.spm.ParkMe;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.UserRepository;






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
		users = new User[] { new User("cret@park.it", "cret@park.it", encoder.encode("Cret"), Roles.ROLE_VIGILANT),
				new User("rocche@park.it", "rocche@park.it",  encoder.encode("Rocche"), Roles.ROLE_DRIVER),
				new User("flash@park.it", "flash@park.it",  encoder.encode("Flash"), Roles.ROLE_PARKING_MANAGER),
				new User("fusaro@turbomondialismo.it","fusaro@turbomondialismo.it", encoder.encode("Fusaro"), Roles.ROLE_ADMIN), };
		repository.deleteAll();
		for (User user : users) {
			repository.save(user);
		}
	}

}
