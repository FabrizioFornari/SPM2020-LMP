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
	@Pattern(regexp="^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})|([0-9]{11})$",message="Invalid Ssn format")  
	private String ssn;
	
	@NotNull(message="email may not be null")
	@NotEmpty(message = "email may not be empty")
	@Pattern(regexp="^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$",message="Invalid Email format")  
	private String email;
	
	@NotNull(message="phone may not be null")
	@NotEmpty(message = "phone may not be empty")
	@Pattern(regexp="^((\\+)39\\)|(00|\\+)39)?(38[890]|34[7-90]|36[680]|33[3-90]|32[89])\\d{7}$", message="Invalid Phone format")
	private String phone;
	
	@NotNull(message="plate may not be null")
	@NotEmpty(message = "plate may not be empty")
	@Pattern(regexp="([A-H]|[K-N]|[P]|[R-T]|[V]|[X-Z]){2}\\d{3}([A-H]|[K-N]|[P]|[R-T]|[V]|[X-Z]){2}",message="Invalid Plate format")
	private String plate;
	
	@NotNull(message="vehicleType may not be null")
	@NotEmpty(message = "vehicleType may not be empty")
	private String vehicleType;
	
	@NotNull(message="password may not be null")
	@NotEmpty(message = "password may not be empty")
	@Size(min=1)
	private String password;

	
	/*-------Constructor------*/
	public Driver(String firstName, String lastName, 
					String ssn,String email,String phone,
					String plate,String vehicleType,
					String password) {
		
		this.firstName=firstName;
		this.lastName=lastName;
		this.ssn= ssn;
		this.phone=phone;
		this.email=email;
		this.plate=plate;
		this.vehicleType=vehicleType;
		this.password=password;
	}
	
	
	
	/*------- ACCESSORY METHODS ---------*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
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
		this.vehicleType = vehicleType.trim();
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
		this.password = password.trim();
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
