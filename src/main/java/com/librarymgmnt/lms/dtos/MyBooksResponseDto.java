package com.librarymgmnt.lms.dtos;

import com.librarymgmnt.lms.models.BookModel;

public class MyBooksResponseDto {
	
	BookModel details;
	String status;
	public MyBooksResponseDto(BookModel details, String status) {
		super();
		this.details = details;
		this.status = status;
	}
	public BookModel getDetails() {
		return details;
	}
	public void setDetails(BookModel details) {
		this.details = details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
