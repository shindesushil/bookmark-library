package com.librarymgmnt.lms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.librarymgmnt.lms.models.BookModel;

public interface BookRepo extends MongoRepository<BookModel, String> {
	
	
	
}
