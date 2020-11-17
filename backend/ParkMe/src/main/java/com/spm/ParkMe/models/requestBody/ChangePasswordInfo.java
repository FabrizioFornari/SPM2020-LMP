package com.spm.ParkMe.models.requestBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.spm.ParkMe.constants.RegexConstants;

public class ChangePasswordInfo {

	@NotNull(message="Password must not be null")
	@NotEmpty(message = "Password must not be empty")
	@Size(min=1)
	private String currentPassword;
	
	@NotNull(message="Password must not be null")
	@NotEmpty(message = "Password must not be empty")
	@Size(min=1)
	private String newPassword;
	
	public ChangePasswordInfo() {}
	
	public ChangePasswordInfo(String currentPassword, String newPassword) {
		this.setCurrentPassword(currentPassword);
		this.setNewPassword(newPassword);
	}
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String password) {
		if(password != null && password != "" && 
				password.length() >= 1) {
			this.currentPassword = password;
		}
		else {
			throw new IllegalArgumentException("Password is invalid");
		}
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String password) {
		if(password != null && password != "" && 
				password.length() >= 1) {
			this.newPassword = password;
		}
		else {
			throw new IllegalArgumentException("Password is invalid");
		}
	}
}

