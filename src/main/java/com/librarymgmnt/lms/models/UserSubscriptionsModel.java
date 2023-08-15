package com.librarymgmnt.lms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("userSubscriptions")
public class UserSubscriptionsModel {
	
	@Id
	String id;
	
	String userId;
	String subscriptionId;
	String expiresOn;
	
	String status = "active";

	public UserSubscriptionsModel(String id, String userId, String subscriptionId, String expiresOn) {
		super();
		this.id = id;
		this.userId = userId;
		this.subscriptionId = subscriptionId;
		this.expiresOn = expiresOn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
