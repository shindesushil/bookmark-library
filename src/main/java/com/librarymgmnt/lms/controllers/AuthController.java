package com.librarymgmnt.lms.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmnt.lms.dtos.AdminLoginResponseDto;
import com.librarymgmnt.lms.dtos.LoginDto;
import com.librarymgmnt.lms.dtos.LoginResponseDto;
import com.librarymgmnt.lms.dtos.RegisterDto;
import com.librarymgmnt.lms.dtos.SubscriptionResponseDto;
import com.librarymgmnt.lms.models.SubscriptionModel;
import com.librarymgmnt.lms.models.UserModel;
import com.librarymgmnt.lms.models.UserSubscriptionsModel;
import com.librarymgmnt.lms.repositories.SubscriptionRepo;
import com.librarymgmnt.lms.repositories.UserRepo;
import com.librarymgmnt.lms.repositories.UserSubscriptionRepo;
import com.librarymgmnt.lms.security.JwtHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	AuthenticationManager authenticationManager;
	UserRepo userRepo;
	PasswordEncoder passwordEncoder;
	JwtHelper jwtHelper;
	
	@Autowired
	UserSubscriptionRepo userSubRepo;
	
	@Autowired
	SubscriptionRepo subRepo;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo,
			PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtHelper = jwtHelper;
	}
	
	@PostMapping("register")
	private ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		
		if(userRepo.existsByUsername(registerDto.getUsername()))
			return new ResponseEntity<String>("User Already Exist!!", HttpStatus.BAD_REQUEST);
		
		UserModel user = new UserModel(
				UUID.randomUUID().toString(),
				registerDto.getFirstName(),
				registerDto.getLastName(),
				registerDto.getUsername(), 
				passwordEncoder.encode(registerDto.getPassword()), 
				"USER");
		
		System.out.println("ID : ==== " + user.getId());
		
		userRepo.save(user);
		
		return new ResponseEntity<String>("User Registered Successfully!", HttpStatus.OK);
	}
	
	@PostMapping("login")
	private ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtHelper.generateToken(authentication);
		
		UserModel user = userRepo.findByUsername(loginDto.getUsername());
		user.setPassword("Password Hidden");
		
		List<UserSubscriptionsModel> userSubscription = userSubRepo.findByUserIdAndStatus(user.getId(), "active");
		SubscriptionModel subscription;
		SubscriptionResponseDto subsResponseDto = null;
		if(userSubscription.size() > 0) {
			String subId = userSubscription.get(0).getSubscriptionId();
			subscription = subRepo.findById(subId).get();
			subsResponseDto = new SubscriptionResponseDto(subscription, userSubscription.get(0).getExpiresOn());
		}else {
			subscription = null;
		}
		
		LoginResponseDto res = new LoginResponseDto();
		
		res.setUser(user);
		res.setSubscription(subsResponseDto);
		res.setToken(token);
		res.setTokenType("Bearer ");
		
		return new ResponseEntity<LoginResponseDto>(res, HttpStatus.OK);
	}
	
	@PostMapping("/admin/login")
	private ResponseEntity<AdminLoginResponseDto> adminLogin(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		AdminLoginResponseDto resp = new AdminLoginResponseDto("", "");
		
		if(authentication.getAuthorities().toString().equals("[ADMIN]")) {
			String token = jwtHelper.generateToken(authentication);
			resp = new AdminLoginResponseDto(token, authentication.getAuthorities().toString());
		}
		
		return new ResponseEntity<AdminLoginResponseDto>(resp, HttpStatus.OK);
		
	}
	
}
