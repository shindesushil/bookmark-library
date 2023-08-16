package com.librarymgmnt.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmnt.lms.models.BookModel;
import com.librarymgmnt.lms.models.SubscriptionModel;
import com.librarymgmnt.lms.repositories.BookRepo;
import com.librarymgmnt.lms.repositories.SubscriptionRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/fetch")
public class FetchController {
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@GetMapping("subscriptions")
	private ResponseEntity<List> getSubscriptions(){
		List<SubscriptionModel> subscriptions = subscriptionRepo.findAll();
		return new ResponseEntity<List>(subscriptions, HttpStatus.OK);
	}

	@GetMapping("books")
	private ResponseEntity<List> getAllBooks() {	
		List<BookModel> books = bookRepo.findAll();
		return new ResponseEntity<List>(books, HttpStatus.OK);
	}
	
}
