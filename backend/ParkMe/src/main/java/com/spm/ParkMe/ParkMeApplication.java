package com.spm.ParkMe;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.repositories.DriverInfoRepository;
import com.spm.ParkMe.repositories.HandicapPermitsRequestsRepository;
import com.spm.ParkMe.repositories.NotificationRepository;
import com.spm.ParkMe.repositories.ParkingLotBookingRepository;
import com.spm.ParkMe.repositories.ParkingLotRepository;
import com.spm.ParkMe.repositories.ParkingLotTicketRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotRepository;
import com.spm.ParkMe.repositories.PersonalParkingLotSubscriptionRepository;
import com.spm.ParkMe.repositories.UserRepository;
import com.spm.ParkMe.repositories.UserSessionRepository;

import static com.spm.ParkMe.constants.ParkingLotCostants.*;


@SpringBootApplication
@EnableScheduling
public class ParkMeApplication extends SpringBootServletInitializer implements CommandLineRunner {


	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DriverInfoRepository driverInfoRepository;
	
	@Autowired
	private HandicapPermitsRequestsRepository handicapRequestsRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotTicketRepository parkingLotTicketRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	
	@Autowired
	private UserSessionRepository userSessionRepository;
	
	@Autowired
	private PersonalParkingLotRepository personalParkingLotRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private PersonalParkingLotSubscriptionRepository personalParkingLotSubscriptionRepository;
	

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(ParkMeApplication.class);
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
				new User("admin@park.it","Diego", "Fusaro", "ZZZZZZ10A01A000Z", "+39 333 3333333","admin@park.it", encoder.encode("Fusaro"), Roles.ROLE_ADMIN), };
		Driver driver = new Driver("rocche@park.it", "Giacomo", "Rocchetti", "ZZZZZZ10A01A000Z", "+39 333 3333333","rocche@park.it",  encoder.encode("Rocche"), "AA000AA", "4 Wheels Standard Vehicle");
		repository.deleteAll();
		for (User user : users) {
			repository.save(user);
		}
		driverInfoRepository.deleteAll();
		driverInfoRepository.save(new DriverInfo(driver));
		handicapRequestsRepository.deleteAll();
		
		parkingLotRepository.deleteAll();
		
		parkingLotRepository.save(PARKING_1);
		parkingLotRepository.save(PARKING_2);
		parkingLotRepository.save(PARKING_3);
		parkingLotRepository.save(PARKING_4);
		parkingLotRepository.save(PARKING_5);
		parkingLotRepository.save(PARKING_6);
		parkingLotRepository.save(PARKING_7);
		
		parkingLotRepository.save(PARKING_8);
		parkingLotRepository.save(PARKING_9);
		parkingLotRepository.save(PARKING_10);
		parkingLotRepository.save(PARKING_11);
		
		
		
		parkingLotBookingRepository.deleteAll();
		
		parkingLotTicketRepository.deleteAll();
		//parkingLotTicketRepository.save(new ParkingLotTicket(STREET, 4, "rocche@park.it", 0.0, 1604581416000L));
		
		userSessionRepository.deleteAll();
		notificationRepository.deleteAll();
		
		personalParkingLotRepository.deleteAll();
		personalParkingLotRepository.save(PERSONAL);
		personalParkingLotRepository.save(PERSONAL2);
		
		personalParkingLotSubscriptionRepository.deleteAll();
//		personalParkingLotSubscriptionRepository.save(new PersonalParkingLotSubscription(
//				"rocche@park.it", System.currentTimeMillis() + 60000, "via Madonna delle Carceri", 51));
	}

}
