package com.librarymgmnt.lms.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmnt.lms.models.SubscriptionModel;
import com.librarymgmnt.lms.repositories.SubscriptionRepo;

@RestController
@RequestMapping("/api/admin/subscription")
public class SubscriptionController {
	
	@Autowired
	SubscriptionRepo subscriptionRepo;
	
	@GetMapping("")
	private ResponseEntity<Optional<SubscriptionModel>> getSubscription(@RequestParam("id") String id){
		Optional<SubscriptionModel> subscription = subscriptionRepo.findById(id);
		return new ResponseEntity<Optional<SubscriptionModel>>(subscription, HttpStatus.OK);
	}
	
	@PostMapping()
	private ResponseEntity<String> addSubscription(
			@RequestParam("duration") int duration,
			@RequestParam("price") String price
			){
		
		SubscriptionModel subscription = new SubscriptionModel(UUID.randomUUID().toString(), duration, price);
		
		subscriptionRepo.save(subscription);
		
		return new ResponseEntity<String>("Subscription Added!", HttpStatus.OK);
		
	}

}
