package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "driverInfos")
public class DriverInfo {
	
	@Id private String id;
	private String username;
	private String plate;
	private String vehicleType;
	private boolean hasHandicap;
	
	public DriverInfo() {}

	public DriverInfo(Driver driver) {
		this.setUsername(driver.getUsername());
		this.setPlate(driver.getPlate());
		this.setVehicleType(driver.getVehicleType());
		this.setHandicap(false);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DriverInfo info = (DriverInfo) o;
		return(info.getPlate().equals(this.getPlate()) &&
				info.getUsername().equals(this.getUsername()) &&
				info.getVehicleType().equals(this.getVehicleType()));
	}

	public boolean getHandicap() {
		return hasHandicap;
	}

	public void setHandicap(boolean handicap) {
		this.hasHandicap = handicap;
	}
}
