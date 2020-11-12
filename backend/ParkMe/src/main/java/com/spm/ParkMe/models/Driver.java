/**
 * 
 */
package com.spm.ParkMe.models;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class Driver {

	@Id private String id;
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	private String firstName;
	
	@NotNull(message="Surname may not be null")
	@NotEmpty(message = "Surname may not be empty")	
	private String lastName;
	
	@NotNull(message="ssn may not be null")
	@NotEmpty(message = "ssn may not be empty")
	@Pattern(regexp="^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})|([0-9]{11})$",message="Invalid ssn format")  
	private String ssn;
	
	@NotNull(message="email may not be null")
	@NotEmpty(message = "email may not be empty")
	@Pattern(regexp="^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$",message="Invalid Email format")  
	private String email;
	
	@NotNull(message="phone may not be null")
	@NotEmpty(message = "phone may not be empty")
	@Pattern(regexp="^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$", message="Invalid Phone format")
	private String phone;
	
	@NotNull(message="plate may not be null")
	@NotEmpty(message = "plate may not be empty")
	@Pattern(regexp="[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}",message="Invalid Plate format")
	private String plate;
	
	@NotNull(message="vehicleType may not be null")
	@NotEmpty(message = "vehicleType may not be empty")
	private String vehicleType;
	
	@NotNull(message="password may not be null")
	@NotEmpty(message = "password may not be empty")
	@Size(min=1)
	private String password;

	
	
	/*-------Constructor------*/
	public Driver() {
		
	}
	
	public Driver(String firstName, String lastName, 
					String ssn,String email,String phone,
					String plate,String vehicleType,
					String password) {
		
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setSsn(ssn);
		this.setPhone(phone);
		this.setEmail(email);
		this.setPlate(plate);
		this.setVehicleType(vehicleType);
		this.setPassword(password);
	}
	
	
	
	/*------- ACCESSORY METHODS ---------*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws IllegalArgumentException{
		if(firstName != null && firstName != "") {
			this.firstName = firstName.trim();
		}
		else {
			throw new IllegalArgumentException("First name is invalid");
		}
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		if(lastName != null && lastName != "") {
			this.lastName = lastName.trim();
		}
		else {
			throw new IllegalArgumentException("Last name is invalid");
		}
	}
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		if(ssn != null && ssn != "" && 
				ssn.matches("^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})|([0-9]{11})$")) {
			this.ssn = ssn;
		}
		else {
			throw new IllegalArgumentException("SSN is invalid");
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email != null && email != "" && 
				email.matches("^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$")) {
			this.email = email;
		}
		else {
			throw new IllegalArgumentException("Email is invalid");
		}
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if(phone != null && phone != "" && 
				phone.matches("^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$")) {
			this.phone = phone;
		}
		else {
			throw new IllegalArgumentException("Phone is invalid");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password != null && password != "") {
			this.password = password.trim();
		}
		else {
			throw new IllegalArgumentException("Password is invalid");
		}
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
