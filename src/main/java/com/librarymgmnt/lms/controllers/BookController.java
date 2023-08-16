package com.librarymgmnt.lms.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.librarymgmnt.lms.dtos.MyBooksResponseDto;
import com.librarymgmnt.lms.models.AppConstants;
import com.librarymgmnt.lms.models.BookModel;
import com.librarymgmnt.lms.models.BorrowingsModel;
import com.librarymgmnt.lms.models.UserModel;
import com.librarymgmnt.lms.repositories.BookRepo;
import com.librarymgmnt.lms.repositories.BorrowingsRepo;
import com.librarymgmnt.lms.repositories.UserRepo;

@CrossOrigin
@RestController
public class BookController {
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private BorrowingsRepo borrowingsRepo;
	
	@PostMapping("/api/admin/book")
	private ResponseEntity<String> addBook(
			@RequestParam("name") String name,
			@RequestParam("author") String author,
			@RequestParam("price") String price,
			@RequestParam("genre") String genre,
			@RequestParam("pages") int pages,
			@RequestParam("description") String description,
			@RequestParam("image") MultipartFile image, 
			@RequestParam("copies") int copies
			) throws IOException {
		
		Binary img = new Binary(BsonBinarySubType.BINARY, image.getBytes());
		
		BookModel book = new BookModel(UUID.randomUUID().toString(), name, author, price, genre, pages, description, img, copies);	
		
		bookRepo.save(book);
		
		return new ResponseEntity<String>("Book Added!", HttpStatus.OK);
	}
	
	@GetMapping("/api/admin/book")
	private ResponseEntity<Optional<BookModel>> getBook(@RequestParam("id") String book_id) {
		
		Optional<BookModel> book = bookRepo.findById(book_id);
		
		return new ResponseEntity<Optional<BookModel>>(book, HttpStatus.OK);
	}
	
	
	@GetMapping("/api/user/books")
	private ResponseEntity<List<MyBooksResponseDto>> getBooksByUser(@RequestParam("userId") String userId)
	{
		List<BorrowingsModel> borrows = borrowingsRepo.getByUserId(userId);
		List<MyBooksResponseDto> books = new ArrayList<MyBooksResponseDto>();
		
		for(int i=0; i<borrows.size(); i++) {
			BookModel book = bookRepo.findById(borrows.get(i).getBookId()).get();
			books.add(new MyBooksResponseDto(book, borrows.get(i).getStatus()));
		}
		
		
		return new ResponseEntity<List<MyBooksResponseDto>>(books, HttpStatus.OK);
		
	}
	
	@PostMapping("/api/user/book/return")
	private ResponseEntity<String> returnBook(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {
		BorrowingsModel borrowing = borrowingsRepo.getByBookIdAndUserId(bookId, userId).get(0);
		if(borrowing == null) {
			return new ResponseEntity<String>("Record Not Found...", HttpStatus.NOT_FOUND);
		}
		if(borrowing.getStatus().equals(AppConstants.BORROW_STATUS_PENDING_BORROW)) {
			borrowingsRepo.delete(borrowing);
			return new ResponseEntity<String>("Record Deleted...", HttpStatus.OK);
		}else if(borrowing.getStatus().equals(AppConstants.BORROW_STATUS_APPROVED_BORROW)) {
			borrowing.setStatus(AppConstants.BORROW_STATUS_PENIDNG_RETURN);
			borrowingsRepo.save(borrowing);
			return new ResponseEntity<String>("Record Updated...", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Something Went Wrong...", HttpStatus.BAD_REQUEST);
	}
	
	

}
