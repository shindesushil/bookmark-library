package com.librarymgmnt.lms.dtos;

import com.librarymgmnt.lms.models.SubscriptionModel;

public class SubscriptionResponseDto {
	SubscriptionModel details;
	String expiresOn;
	public SubscriptionResponseDto(SubscriptionModel details, String expiresOn) {
		super();
		this.details = details;
		this.expiresOn = expiresOn;
	}
	public SubscriptionModel getDetails() {
		return details;
	}
	public void setDetails(SubscriptionModel details) {
		this.details = details;
	}
	public String getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}
	
}
