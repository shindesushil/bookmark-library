package com.librarymgmnt.lms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.librarymgmnt.lms.models.BorrowingsModel;
import java.util.List;


public interface BorrowingsRepo extends MongoRepository<BorrowingsModel, String> {
	List<BorrowingsModel> getByUserId(String userId);
	List<BorrowingsModel> getByBookIdAndUserId(String bookId, String userId);
	List<BorrowingsModel> getByStatus(String status);
}
