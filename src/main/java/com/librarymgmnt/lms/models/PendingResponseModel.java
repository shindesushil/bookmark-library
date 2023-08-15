package com.librarymgmnt.lms.models;

public class PendingResponseModel {

	UserModel user;
	BookModel book;
	BorrowingsModel recordDetails;
	
	public PendingResponseModel(UserModel user, BookModel book, BorrowingsModel recordDetails) {
		super();
		this.user = user;
		this.book = book;
		this.recordDetails = recordDetails;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public BookModel getBook() {
		return book;
	}

	public void setBook(BookModel book) {
		this.book = book;
	}

	public BorrowingsModel getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(BorrowingsModel recordDetails) {
		this.recordDetails = recordDetails;
	}
	
	
	
}
