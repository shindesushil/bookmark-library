package com.librarymgmnt.lms.dtos;

import com.librarymgmnt.lms.models.SubscriptionModel;
import com.librarymgmnt.lms.models.UserModel;

public class LoginResponseDto {
	
	UserModel user;
	SubscriptionResponseDto subscription;
	String token;
	String tokenType;
	
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public SubscriptionResponseDto getSubscription() {
		return subscription;
	}
	public void setSubscription(SubscriptionResponseDto subscription) {
		this.subscription = subscription;
	}
	
	
	
}
