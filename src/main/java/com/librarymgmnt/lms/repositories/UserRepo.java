package com.librarymgmnt.lms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.librarymgmnt.lms.models.UserModel;

public interface UserRepo extends MongoRepository<UserModel, String> {

	UserModel findByUsername(String username);
	Boolean existsByUsername(String username);
	
}
