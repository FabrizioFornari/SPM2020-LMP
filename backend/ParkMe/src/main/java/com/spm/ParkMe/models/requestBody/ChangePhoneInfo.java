package com.spm.ParkMe.models.requestBody;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.spm.ParkMe.constants.RegexConstants;


public class ChangePhoneInfo {

	@NotNull(message="Current phone must not be null")
	@NotEmpty(message = "Current phone must not be empty")
	@Pattern(regexp=RegexConstants.PHONE_REGEX,message="Invalid Phone format")
	private String currentPhone;
	
	@NotNull(message="New Phone must not be null")
	@NotEmpty(message = "New Phone must not be empty")
	@Pattern(regexp=RegexConstants.PHONE_REGEX,message="Invalid Phone format")
	private String newPhone;
	
	public ChangePhoneInfo() {
		
	}
	
	public ChangePhoneInfo(String currentPhone, String newPhone) {
		
		this.setCurrentPhone(currentPhone);
		this.setNewPhone(newPhone);
	}
	
	public String getCurrentPhone() {
		return currentPhone;
	}
	public void setCurrentPhone(String phone) {
		if(phone != null && phone != "" && 
				phone.matches(RegexConstants.PHONE_REGEX)) {
			this.currentPhone = phone;
		}
		else {
			throw new IllegalArgumentException("Phone is invalid");
		}
	}
	
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String phone) {
		if(phone != null && phone != "" && 
				phone.matches(RegexConstants.PHONE_REGEX)) {
			this.newPhone = phone;
		}
		else {
			throw new IllegalArgumentException("Phone is invalid");
		}
	}
	
	
}
