package com.spm.ParkMe.constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.models.Coordinates;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.ParkingLot;
import com.spm.ParkMe.models.ParkingLotBooking;
import com.spm.ParkMe.models.ParkingLotTicket;
import com.spm.ParkMe.models.requestBody.ChangeParkingLot;
import static com.spm.ParkMe.constants.UserInfoConstants.DRIVER_MAIL;

public class ParkingLotCostants {

	public static final String STREET = "via Madonna delle Carceri";
	public static final String STREET2 = "via Aldo Moro";
	public static final String CAR = "4 Wheels Standard Vehicle";
	public static final String MOTORCYCLE = "2 Wheels Vehicle";

	public static final String VALID_STREET = "via aldo moro, 32";
	public static final Integer VALID_NUMBROFPARKINGLOT = 3;
	public static final Boolean VALID_ISHANDICAPPARKINGLOT = false;
	public static final Double VALID_PRICEPERHOURS = 0.5;
	public static final Coordinates VALID_COORDINATES= new Coordinates("23.56","34.6");

	public static final String NEW_STREET="via madonna delle carceri";
	public static final Integer NEW_NUMBEROFPARKINGLOT= 4;
	public static final Boolean NEW_ISHANDICAPPARKINGLOT=false;
	public static final Double NEW_PRICEPERHOURS=0.7;
	public static final String NEW_TYPEOFVEHICLE="CAR";
	public static final String NEW_LATITUDE= "34.65";
	public static final String NEW_LONGITUDE= "12.76";
	
	public static final ParkingLot PARKINGLOT_OBJECT = new ParkingLot(VALID_STREET, VALID_NUMBROFPARKINGLOT, VALID_ISHANDICAPPARKINGLOT, VALID_PRICEPERHOURS, CAR, VALID_COORDINATES);

	public static final ChangeParkingLot CHANGE_PARKINGLOT_OBJECT= new ChangeParkingLot(VALID_STREET, VALID_NUMBROFPARKINGLOT, NEW_STREET,NEW_NUMBEROFPARKINGLOT,NEW_ISHANDICAPPARKINGLOT,NEW_PRICEPERHOURS,NEW_TYPEOFVEHICLE,NEW_LATITUDE,NEW_LONGITUDE);

	//available parking lots
	public static final ParkingLot PARKING_1 = new ParkingLot(STREET, 1, false, 0.0, MOTORCYCLE, new Coordinates("43.13980271107574", "13.069041178142996"));
	public static final ParkingLot PARKING_2 = new ParkingLot(STREET, 2, false, 0.0, MOTORCYCLE, new Coordinates("43.13978460721184", "13.069011003293157"));
	public static final ParkingLot PARKING_3 = new ParkingLot(STREET, 3, false, 0.0, CAR, new Coordinates("43.139828643628235", "13.068880916162737"));
	public static final ParkingLot PARKING_4 = new ParkingLot(STREET, 4, false, 0.0, CAR, new Coordinates("43.139696044989535", "13.0685489928145"));
	public static final ParkingLot PARKING_5 = new ParkingLot(STREET, 5, false, 0.0, CAR, new Coordinates("43.13957176408314", "13.068219081101638"));
	public static final ParkingLot PARKING_6 = new ParkingLot(STREET, 6, false, 1.0, CAR, new Coordinates("43.13952577029873", "13.068314299516688"));
	public static final ParkingLot PARKING_7 = new ParkingLot(STREET, 7, true, 0.0, CAR, new Coordinates("43.139561488878996", "13.068390071917397"));
	
	public static final ParkingLot PARKING_8 = new ParkingLot(STREET2, 8, false, 1.0, CAR, new Coordinates("43.141510369283516", "13.067885567988407"));
	public static final ParkingLot PARKING_9 = new ParkingLot(STREET2, 9, false, 1.0, CAR, new Coordinates("43.14122658629402", "13.06735449063123"));
	public static final ParkingLot PARKING_10 = new ParkingLot(STREET2, 10, false, 0.0, CAR, new Coordinates("43.14130878564028", "13.067475190030587"));
	public static final ParkingLot PARKING_11 = new ParkingLot(STREET2, 11, true, 0.0, CAR, new Coordinates("43.14135967089449", "13.067767550797923"));

	//ParkingLot Booking

	public static final ParkingLotBooking PARKING_BOOKING= new ParkingLotBooking(STREET, 4, DRIVER_MAIL, 1604574216000L, new Coordinates("43.139696044989535", "13.0685489928145"), 0.0 );

	//ParkingLot Ticket
	public static final ParkingLotTicket PARKING_TICKET= new ParkingLotTicket(STREET, 4, DRIVER_MAIL, 0.0, 1604581416000L);
}
