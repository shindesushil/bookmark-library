package com.librarymgmnt.lms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.librarymgmnt.lms.models.UserSubscriptionsModel;
import java.util.List;


public interface UserSubscriptionRepo extends MongoRepository<UserSubscriptionsModel, String> {

	@Query("{userId: ?0, status: ?1}")
	List<UserSubscriptionsModel> findByUserIdAndStatus(String userId, String status);
	
}
