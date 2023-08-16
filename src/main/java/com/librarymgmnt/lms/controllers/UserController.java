package com.librarymgmnt.lms.controllers;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.librarymgmnt.lms.dtos.UpdateDpRequestDto;
import com.librarymgmnt.lms.models.UserModel;
import com.librarymgmnt.lms.repositories.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	

	@PostMapping("update-dp")
	private ResponseEntity<UserModel> updateProfilePicture(
			@RequestParam MultipartFile photo,
			@RequestParam String userId
			) throws IOException {
		
		
		Binary img = new Binary(BsonBinarySubType.BINARY, photo.getBytes());
		
		UserModel user = userRepo.findById(userId).get();
		
		user.setImage(img);
		
		userRepo.save(user);
		
		
		return new ResponseEntity<UserModel>(user, HttpStatus.OK);		
		
	}

}
