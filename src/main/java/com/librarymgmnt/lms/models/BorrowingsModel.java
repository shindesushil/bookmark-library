package com.librarymgmnt.lms.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("borrowings")
public class BorrowingsModel {

	@Id
	String id;
	
	String userId;
	String bookId;
	String borrowDate = new Date().toString();
	String status = AppConstants.BORROW_STATUS_PENDING_BORROW;
	int fineAmount = 0;
	
	public BorrowingsModel(String id, String userId, String bookId) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
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

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}
	
		
}
