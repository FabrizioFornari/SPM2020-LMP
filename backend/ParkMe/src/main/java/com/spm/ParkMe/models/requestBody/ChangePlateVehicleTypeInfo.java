package com.spm.ParkMe.models.requestBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.spm.ParkMe.constants.RegexConstants;

public class ChangePlateVehicleTypeInfo {

	@NotNull(message="Current Plate must not be null")
	@NotEmpty(message = "Current Plate must not be empty")
	@Pattern(regexp=RegexConstants.PLATE_REGEX,message="Invalid Plate format")
	private String currentPlate;
	@NotNull(message="Current Plate must not be null")
	@NotEmpty(message = "Current Plate must not be empty")
	@Pattern(regexp=RegexConstants.PLATE_REGEX,message="Invalid Plate format")
	private String newPlate;
	@NotNull(message="new Plate must not be null")
	@NotEmpty(message = "new Plate must not be empty")
	private String currentVehicleType;
	@NotNull(message="new Plate must not be null")
	@NotEmpty(message = "new Plate must not be empty")
	private String newVehicleType;
	
	public ChangePlateVehicleTypeInfo() {
		
	}
	
	public ChangePlateVehicleTypeInfo(String currentPlate, String newPlate, String currentVehicleType, String newVehicleType) {
		
		this.setCurrentPlate(currentPlate);
		this.setNewPlate(newPlate);
		this.setCurrentVehicleType(currentVehicleType);
		this.setNewVehicleType(newVehicleType);
	}

	public String getCurrentPlate() {
		return currentPlate;
	}
	public void setCurrentPlate(String plate) {
		if(plate != null && plate != "" && 
				plate.matches(RegexConstants.PLATE_REGEX)) {
			this.currentPlate = plate;
		}
		else {
			throw new IllegalArgumentException("Plate is invalid");
		}
	}
	
	public String getNewPlate() {
		return newPlate;
	}
	public void setNewPlate(String plate) {
		if(plate != null && plate != "" && 
				plate.matches(RegexConstants.PLATE_REGEX)) {
			this.newPlate = plate;
		}
		else {
			throw new IllegalArgumentException("Plate is invalid");
		}
	}
	
	public String getCurrentVehicleType() {
		return currentVehicleType;
	}
	public void setCurrentVehicleType(String vehicleType) {
		if(vehicleType != null && vehicleType != "" ) {
			this.currentVehicleType = vehicleType;
		}
		else {
			throw new IllegalArgumentException("vehicleType is invalid");
		}
	}
	
	public String getNewVehicleType() {
		return newVehicleType;
	}
	public void setNewVehicleType(String vehicleType) {
		if(vehicleType != null && vehicleType != "" ) {
			this.newVehicleType = vehicleType;
		}
		else {
			throw new IllegalArgumentException("vehicleType is invalid");
		}
	}
}
