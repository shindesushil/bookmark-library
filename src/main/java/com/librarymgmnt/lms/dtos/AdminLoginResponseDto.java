package com.librarymgmnt.lms.dtos;

public class AdminLoginResponseDto {
	String token;
	String role;
	public AdminLoginResponseDto(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
