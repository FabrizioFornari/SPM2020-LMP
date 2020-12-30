package com.spm.ParkMe.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.spm.ParkMe.enums.CategoryNotification;
import com.spm.ParkMe.enums.Status;
import com.spm.ParkMe.enums.StatusNotification;

public class Notification {

	@Id private String id;

	@NotNull(message="title must not be null")
	@NotEmpty(message = "title must not be empty")
	private String title;
	@NotNull(message="text must not be null")
	@NotEmpty(message = "text must not be empty")
	private String text;
	@NotNull(message="username must not be null")
	@NotEmpty(message = "username must not be empty")
	private String username;
	private CategoryNotification categoryNotification;
	@NotNull(message="Timestamp must not be null")
	private long timeStamp;
	private StatusNotification statusNotification;
	
	//-------- CONSTRUCTOR --------
	public Notification() {
		
	}

	public Notification(String title, String text, String username, long timeStamp ) {
		this.setTitle(title);
		this.setText(text);
		this.setUsername(username);
		this.setTimeStamp(timeStamp);
		this.setStatusNotification(statusNotification.NEW);
		this.setCategoryNotification(null);
	}
	
	
	//------------ ACCESSOR ------------
	
	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if(text != null && text != "" ) {
			this.text = text;
		}
		else {
			throw new IllegalArgumentException("text is invalid");
			}		
		}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username != null && username != "" ) {
			this.username = username;
		}
		else {
			throw new IllegalArgumentException("username is invalid");
			}		
		}
	
	public CategoryNotification getCategoryNotification() {
		return categoryNotification;
	}

	public void setCategoryNotification(CategoryNotification categoryNotification) {
		this.categoryNotification = categoryNotification;
	}
	
	public StatusNotification getStatusNotification() {
		return statusNotification;
	}

	public void setStatusNotification(StatusNotification statusNotification) {
		this.statusNotification = statusNotification;
	}
	
	public long getTimestamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Long timeStamp) {
		if(timeStamp != null) {
			this.timeStamp = timeStamp;
		}
		else {
			throw new IllegalArgumentException("timeStamp is invalid");
			}	
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title != null && title != "" ) {
			this.title = title;
		}
		else {
			throw new IllegalArgumentException("title is invalid");
			}	
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Notification notification = (Notification) o;
		return(notification.getText().equals(this.getText()) &&
				(notification.getUsername()).equals(this.getUsername()) &&
				notification.getStatusNotification().equals(this.getStatusNotification()) &&
				((Long)notification.getTimestamp()).equals((Long)this.getTimestamp())&&
				(notification.getCategoryNotification().equals(this.getCategoryNotification())));
		
	}

}
