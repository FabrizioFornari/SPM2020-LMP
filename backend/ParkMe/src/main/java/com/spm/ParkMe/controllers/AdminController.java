package com.spm.ParkMe.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.ParkMe.enums.Roles;
import com.spm.ParkMe.models.AdminHandicapRequestAcceptance;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.DriverInfo;
import com.spm.ParkMe.models.HandicapPermitsRequest;
import com.spm.ParkMe.models.MessageResponse;
import com.spm.ParkMe.models.ParkingManager;
import com.spm.ParkMe.models.User;
import com.spm.ParkMe.models.Vigilant;
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

import static com.spm.ParkMe.constants.EndpointContants.*;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_1;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_10;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_11;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_2;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_3;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_4;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_5;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_6;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_7;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_8;
import static com.spm.ParkMe.constants.ParkingLotCostants.PARKING_9;
import static com.spm.ParkMe.constants.ParkingLotCostants.PERSONAL;
import static com.spm.ParkMe.constants.ParkingLotCostants.PERSONAL2;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*", maxAge =3600)
@RestController
@RequestMapping(ADMIN_ENDPOINT)
public class AdminController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private HandicapPermitsRequestsRepository handicapRepository;
	
	@Autowired
	private DriverInfoRepository driverInfoRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private ParkingLotBookingRepository parkingLotBookingRepository;
	
	@Autowired
	private ParkingLotTicketRepository parkingLotTicketRepository;
	
	@Autowired
	private UserSessionRepository userSessionRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private PersonalParkingLotRepository personalParkingLotRepository;
	
	@Autowired
	private PersonalParkingLotSubscriptionRepository personalParkingLotSubscriptionRepository;
	
	@Autowired
	private PasswordEncoder encoder;
		
	@PostMapping(PARKING_MANAGER_REGISTRATION_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity parkingManagerRegistration(@Valid @RequestBody ParkingManager pmanager)  {
		if(!repository.existsByUsername(pmanager.getUsername())) {
			pmanager.setUsername(pmanager.getEmail());
			pmanager.setPassword(encoder.encode(pmanager.getPassword()));
			pmanager.setRole(Roles.ROLE_PARKING_MANAGER);
			repository.save(pmanager);
			return new ResponseEntity(HttpStatus.OK);
		}else {
			
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
	
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(VIGILANT_REGISTRATION_ENDPOINT)
	public ResponseEntity vigilantRegistration(@Valid @RequestBody Vigilant vigilant)  {
		if(!repository.existsByUsername(vigilant.getUsername())){
			vigilant.setUsername(vigilant.getEmail());
			vigilant.setPassword(encoder.encode(vigilant.getPassword()));
			vigilant.setRole(Roles.ROLE_VIGILANT);
			repository.save(vigilant);
			return new ResponseEntity(HttpStatus.OK);
		}else 
		{
			return new ResponseEntity(HttpStatus.CONFLICT);
			}
	
	}
	
	@GetMapping(ADMIN_GET_ALL_HANDICAP_PERMITS_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public  List<HandicapPermitsRequest> allHandicapPermits()  {
		return handicapRepository.findAll();
	}
	
	@GetMapping(ADMIN_GET_NOT_PROCESSED_HANDICAP_PERMITS_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public  List<HandicapPermitsRequest> notProcessedHandicapPermits()  {
		return handicapRepository.findAll().stream().filter(req -> !req.isProcessed()).collect(Collectors.toList());
	}
	
	@GetMapping(ADMIN_GET_PROCESSED_HANDICAP_PERMITS_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public  List<HandicapPermitsRequest> processedHandicapPermits()  {
		return handicapRepository.findAll().stream().filter(req -> req.isProcessed()).collect(Collectors.toList());	
	}
	
	@PostMapping(ADMIN_SET_HANDICAP_PERMITS_ENDPOINT)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> setHandicapPermits(@NotNull @RequestBody AdminHandicapRequestAcceptance acceptance ) {
		HandicapPermitsRequest handicapPermits= handicapRepository.findByUsername(acceptance.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Cannot find handicap request with username " + acceptance.getUsername()));
		DriverInfo driverInfo = driverInfoRepository.findByUsername(acceptance.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username " + acceptance.getUsername()));
		handicapPermits.setProcessed(true);
		handicapPermits.setAccepted(acceptance.getIsAccepted());
		driverInfo.setHandicap(acceptance.getIsAccepted());
		handicapRepository.delete(handicapPermits);
		driverInfoRepository.save(driverInfo);
		return ResponseEntity.ok(handicapPermits);
	}
	
	
	//ONLY FOR DEVELPMENT PURPOSE
	@GetMapping("/reset")
	public ResponseEntity<?> resetDB(){
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
		handicapRepository.deleteAll();
		
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
		
		userSessionRepository.deleteAll();
		notificationRepository.deleteAll();
		
		personalParkingLotRepository.deleteAll();
		personalParkingLotRepository.save(PERSONAL);
		personalParkingLotRepository.save(PERSONAL2);
		
		personalParkingLotSubscriptionRepository.deleteAll();
		
		return ResponseEntity.ok(new MessageResponse("DB reset successfully"));
	}
}
