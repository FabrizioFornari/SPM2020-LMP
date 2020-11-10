/**
 * 
 */
package com.spm.ParkMe.models;

import org.springframework.data.annotation.Id;




public class Driver {

	@Id private String id;
	
	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	private String phone;
	private String plate;
	private String vehicleType;
	private String password;
	private boolean isLoading;
	
	
	/*-------Constructor------*/
	public Driver() {
		

	}
	
	
	
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
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPlate() {
		return plate;
	}



	public void setPlate(String plate) {
		this.plate = plate;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isLoading() {
		return isLoading;
	}



	public void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}
	
	public boolean isValid() {
		return (this.getFirstName() != ""
				&& this.getLastName() != "" 
				&& this.getEmail() != ""
				&& this.getPassword() != ""
				&& this.getPhone() != ""
				&& this.getPlate() != ""
				&& this.getSsn() != ""
				&& this.getVehicleType() != "");
	}
	
}
