package com.spm.ParkMe.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.spm.ParkMe.enums.Roles;

@Document(collection = "users")
public class User {
	
	@Id private String id;

	@NotNull(message="First name must not be null")
	@NotEmpty(message = "First name must not be empty")
	private String firstName;
	
	@NotNull(message="Last name must not be null")
	@NotEmpty(message = "Last name must not be empty")	
	private String lastName;
	
	@NotNull(message="SSN must not be null")
	@NotEmpty(message = "SSN must not be empty")
	@Pattern(regexp="^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})|([0-9]{11})$",message="Invalid SSN format")  
	private String ssn;
	
	@NotNull(message="Email must not be null")
	@NotEmpty(message = "Email must not be empty")
	@Pattern(regexp="^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$",message="Invalid Email format")  
	private String email;
	
	@NotNull(message="Username must not be null")
	@NotEmpty(message = "Username must not be empty")
	@Pattern(regexp="^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$",message="Invalid Email format")  
	private String username;
	
	@NotNull(message="Phone must not be null")
	@NotEmpty(message = "Phone must not be empty")
	@Pattern(regexp="^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$", message="Invalid Phone format")
	private String phone;
	
	@NotNull(message="Password must not be null")
	@NotEmpty(message = "Password must not be empty")
	@Size(min=1)
	private String password;
	
	private Roles role;
	
	public User() {}
	
	public User(String username, String firstName, String lastName, String ssn, String phone, String email, String password, Roles role) {
		this.setUsername(username);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setSsn(ssn);
		this.setPhone(phone);
		this.setEmail(email);
		this.setPassword(password);
		this.setRole(role);
	}
	
	
	/*-----------ACCESSORY METHODS--------------*/
	
	public String getId() {
		return this.id;
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

	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		if(firstName != null && firstName != "") {
			this.firstName = firstName.trim();
		}
		else {
			throw new IllegalArgumentException("First name is invalid");
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return(user.getEmail().equals(this.getEmail()) &&
				user.getId().equals(this.getId()) &&
				user.getPassword().equals(this.getPassword()) &&
				user.getRole().equals(this.getRole()));
	}

	
}
