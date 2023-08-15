package com.librarymgmnt.lms.dtos;

import org.springframework.web.multipart.MultipartFile;

public class UpdateDpRequestDto {

	String userId;
	MultipartFile photo;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
	
	
}
