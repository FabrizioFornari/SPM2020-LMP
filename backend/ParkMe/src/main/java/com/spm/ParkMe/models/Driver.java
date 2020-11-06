/**
 * 
 */
package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;
/**
 * @author manuel
 *
 */
public class Driver {

	@Id private String id;
	
	private String firstName;
	private String lastName;
	private String username;
	private String ssn;
	private String licensePlace;
	private String vehicleType;
	private String hashedPassword;
	
	/*------- ACCESSORY METHODS ---------*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getLicensePlace() {
		return licensePlace;
	}
	public void setLicensePlace(String licensePlace) {
		this.licensePlace = licensePlace;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	
}
