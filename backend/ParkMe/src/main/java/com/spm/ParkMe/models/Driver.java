/**
 * 
 */
package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.spm.ParkMe.enums.Roles;




public class Driver extends User{
	
	@NotNull(message="Plate must not be null")
	@NotEmpty(message = "Plate must not be empty")
	@Pattern(regexp="[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}",message="Invalid Plate format")
	private String plate;
	
	@NotNull(message="Vehicle type must not be null")
	@NotEmpty(message = "Vehicle type must not be empty")
	private String vehicleType;
	
	public Driver(String username, String firstName, String lastName, String ssn, String phone, String email, String password, String plate, String vehicleType) {
		super(username, firstName, lastName, ssn, phone, email, password, Roles.ROLE_DRIVER);
		this.setPlate(plate);
		this.setVehicleType(vehicleType);
	}
	
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		if(plate != null && plate != "" && 
				plate.matches("[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}")) {
			this.plate = plate;
		}
		else {
			throw new IllegalArgumentException("Plate is invalid");
		}
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {

		if(vehicleType != null && vehicleType != "") {
			this.vehicleType = vehicleType.trim();
		}
		else {
			throw new IllegalArgumentException("Vehicle type is invalid");
		}
	}
}
