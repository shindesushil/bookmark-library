package com.librarymgmnt.lms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.librarymgmnt.lms.models.SubscriptionModel;

public interface SubscriptionRepo extends MongoRepository<SubscriptionModel, String> {
	
}
