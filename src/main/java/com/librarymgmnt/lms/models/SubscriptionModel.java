package com.librarymgmnt.lms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subscriptions")
public class SubscriptionModel {
	
	@Id
	private String id;
	
	private int duration; // In Months
	private String price;
	
	
	public SubscriptionModel(String id, int duration, String price) {
		super();
		this.id = id;
		this.duration = duration;
		this.price = price;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
	
	

}
