package com.librarymgmnt.lms.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {
	
	@GetMapping("/")
	public String sayHello() {
		return "Hello Coder!";
	}

}
