package com.librarymgmnt.lms.dtos;

import com.librarymgmnt.lms.models.SubscriptionModel;

public class CompleteTransactionResponseDto {
	
	String subscriptionId;
	int duration;
	String price;
	String expiresOn;
	
	public CompleteTransactionResponseDto(String subscriptionId, int duration, String price, String expiresOn) {
		super();
		this.subscriptionId = subscriptionId;
		this.duration = duration;
		this.price = price;
		this.expiresOn = expiresOn;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}
	
	
	
	
	
}
