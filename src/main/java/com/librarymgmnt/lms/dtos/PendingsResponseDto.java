package com.librarymgmnt.lms.dtos;

import java.util.List;

import com.librarymgmnt.lms.models.BorrowingsModel;
import com.librarymgmnt.lms.models.PendingResponseModel;

public class PendingsResponseDto {

	List<PendingResponseModel> borrowings;
	List<PendingResponseModel> returns;
	
	public PendingsResponseDto(List<PendingResponseModel> borrowings, List<PendingResponseModel> returns) {
		super();
		this.borrowings = borrowings;
		this.returns = returns;
	}

	public List<PendingResponseModel> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(List<PendingResponseModel> borrowings) {
		this.borrowings = borrowings;
	}

	public List<PendingResponseModel> getReturns() {
		return returns;
	}

	public void setReturns(List<PendingResponseModel> returns) {
		this.returns = returns;
	}
	
	
	
	
	
}
