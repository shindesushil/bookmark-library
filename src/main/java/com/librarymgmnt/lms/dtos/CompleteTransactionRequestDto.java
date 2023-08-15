package com.librarymgmnt.lms.dtos;

public class CompleteTransactionRequestDto {
	
	String orderId;
	String paymentId;
	String userId;
	String subscriptionId;
	
	public CompleteTransactionRequestDto(String orderId, String paymentId, String userId, String subscriptionId) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.userId = userId;
		this.subscriptionId = subscriptionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
	
	
	

}
