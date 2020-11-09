package com.spm.ParkMe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	public void run(String... args) throws Exception{
		repository.deleteAll();

	    repository.save(new User("Giacomo", "Rocchetti", "Rocche", "blabla"));
	    repository.save(new User("Manuel", "Cretone", "Cret", "blabla"));

	    
	    for (User user : repository.findAll()) {
	      System.out.println(user.getUserName());
	    }
	    System.out.println();

	    System.out.println(repository.findByUsername("Cret").getUserName());
	}

}
