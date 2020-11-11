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
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	@Pattern(regexp="/^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i;",message="Invalid Ssn format")  
	private String ssn;
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	@Pattern(regexp="/^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/",message="Invalid Email format")  
	private String email;
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	@Pattern(regexp="/^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$/", message="Invalid Pgone format")
	private String phone;
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	@Pattern(regexp="/[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}/g",message="Invalid Plate format")
	private String plate;
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	private String vehicleType;
	
	@NotNull(message="Name may not be null")
	@NotEmpty(message = "Name may not be empty")
	@Size(min=1)
	private String password;

	
	/*-------Constructor------*/
	public Driver() {
		

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
