package com.spm.ParkMe.models;


public class Vigilant {

	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	private Number phone;
	private String password;
	
	
	/*-------Constructor------*/
	public Vigilant() {
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Number getPhone() {
		return phone;
	}
	public void setPhone(Number phone) {
		this.phone = phone;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isValid() {
		return (this.getFirstName() != ""
				&& this.getLastName() != "" 
				&& this.getEmail() != ""
				&& this.getPassword() != ""
				&& this.getPhone() != null
				&& this.getSsn() != ""
				);
	}

}
