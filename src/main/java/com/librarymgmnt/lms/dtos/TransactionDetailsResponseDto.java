package com.librarymgmnt.lms.dtos;

public class TransactionDetailsResponseDto {
	
	String orderId;
	String currency;
	Integer amount;
	String key;

	public TransactionDetailsResponseDto(String orderId, Integer amount, String currency, String key) {
		super();
		this.orderId = orderId;
		this.currency = currency;
		this.amount = amount;
		this.key = key;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}

	
	
	
}
