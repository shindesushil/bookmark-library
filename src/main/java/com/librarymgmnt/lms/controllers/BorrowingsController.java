package com.librarymgmnt.lms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmnt.lms.dtos.PendingsResponseDto;
import com.librarymgmnt.lms.models.AppConstants;
import com.librarymgmnt.lms.models.BookModel;
import com.librarymgmnt.lms.models.BorrowingsModel;
import com.librarymgmnt.lms.models.PendingResponseModel;
import com.librarymgmnt.lms.models.UserModel;
import com.librarymgmnt.lms.repositories.BookRepo;
import com.librarymgmnt.lms.repositories.BorrowingsRepo;
import com.librarymgmnt.lms.repositories.UserRepo;

@RestController
@RequestMapping("/api")
public class BorrowingsController {
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private BorrowingsRepo borrowingsRepo;
	
	@Autowired
	UserRepo userRepo;
	
	// Create new record
	@GetMapping("/user/borrow")
	private ResponseEntity<String> borrowBook(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {
		BookModel book = bookRepo.findById(bookId).get();
		if(book.getCopiesAvailable() == 0) {
			return new ResponseEntity<String>("Book Currently Not Available...", HttpStatus.NOT_FOUND);
		}
		BorrowingsModel borrowing = new BorrowingsModel(UUID.randomUUID().toString(), userId, bookId);
		borrowingsRepo.save(borrowing);

		return new ResponseEntity<String>("Success...", HttpStatus.OK);
	}

	
	//Fetch pending records for admin
	@GetMapping("/admin/pending")
	private ResponseEntity<PendingsResponseDto> getAllPendings() {
		List<BorrowingsModel> borrowings = borrowingsRepo.getByStatus(AppConstants.BORROW_STATUS_PENDING_BORROW);
		List<BorrowingsModel> returns = borrowingsRepo.getByStatus(AppConstants.BORROW_STATUS_PENIDNG_RETURN);
		
		List<PendingResponseModel> b = new ArrayList<>();
		for(int i=0; i<borrowings.size(); i++) {
			UserModel user = userRepo.findById(borrowings.get(i).getUserId()).orElse(null);
			if(user != null)
				user.setPassword("Hidden");
			BookModel book = bookRepo.findById(borrowings.get(i).getBookId()).orElse(null);
			PendingResponseModel pending = new PendingResponseModel(user, book, borrowings.get(i));
			b.add(pending);
		}
		
		List<PendingResponseModel> r = new ArrayList<>();
		for(int i=0; i<returns.size(); i++) {
			UserModel user = userRepo.findById(returns.get(i).getUserId()).orElse(null);
			if(user != null)
				user.setPassword("Hidden");
			BookModel book = bookRepo.findById(returns.get(i).getBookId()).orElse(null);
			PendingResponseModel pending = new PendingResponseModel(user, book, returns.get(i));
			r.add(pending);
		}
		
		return new ResponseEntity<PendingsResponseDto>(new PendingsResponseDto(b, r), HttpStatus.OK);
	}
	
	@PostMapping("/admin/borrow/approve")
	private ResponseEntity<String> approveBorrow(@RequestParam("recordId") String recordId) {
		BorrowingsModel record = borrowingsRepo.findById(recordId).get();
		record.setStatus(AppConstants.BORROW_STATUS_APPROVED_BORROW);
		
		BookModel book = bookRepo.findById(record.getBookId()).get();
		book.setCopiesAvailable(book.getCopiesAvailable() - 1);
		
		bookRepo.save(book);
		borrowingsRepo.save(record);
		return new ResponseEntity<String>("Operation Success...", HttpStatus.OK);
	}
	
	@PostMapping("/admin/borrow/reject")
	private ResponseEntity<String> rejectBorrow(@RequestParam("recordId") String recordId){
		BorrowingsModel record = borrowingsRepo.findById(recordId).get();
		borrowingsRepo.delete(record);
		return new ResponseEntity<String>("Operation Success...", HttpStatus.OK);
	}
	
	@PostMapping("/admin/return/approve")
	private ResponseEntity<String> approveReturn(@RequestParam("recordId") String recordId) {
		BorrowingsModel record = borrowingsRepo.findById(recordId).get();
		
		BookModel book = bookRepo.findById(record.getBookId()).get();
		book.setCopiesAvailable(book.getCopiesAvailable() + 1);
		
		bookRepo.save(book);
		borrowingsRepo.delete(record);
		return new ResponseEntity<String>("Operation Success...", HttpStatus.OK);
	}
	
}
