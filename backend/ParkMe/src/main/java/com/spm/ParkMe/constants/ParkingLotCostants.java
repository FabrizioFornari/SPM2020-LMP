package com.spm.ParkMe.constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.models.Coordinates;
import com.spm.ParkMe.models.Driver;
import com.spm.ParkMe.models.ParkingLot;

public class ParkingLotCostants {

	public static final String VALID_STREET = "via aldo moro, 32";
	public static final Integer VALID_NUMBROFPARKINGLOT = 3;
	public static final Boolean VALID_ISHANDICAPPARKINGLOT = false;
	public static final Double VALID_PRICEPERHOURS = 0.5;
	public static final String VALID_TYPEOFVEHICLE  = "CAR";
	public static final Coordinates VALID_COORDINATES= new Coordinates(23.56,34.6);

	
	public static final ParkingLot PARKINGLOT_OBJECT = new ParkingLot(VALID_STREET, VALID_NUMBROFPARKINGLOT, VALID_ISHANDICAPPARKINGLOT, VALID_PRICEPERHOURS, VALID_TYPEOFVEHICLE, VALID_COORDINATES);

	
}
