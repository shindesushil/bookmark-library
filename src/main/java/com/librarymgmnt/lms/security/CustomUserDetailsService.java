package com.librarymgmnt.lms.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.librarymgmnt.lms.models.UserModel;
import com.librarymgmnt.lms.repositories.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepo userRepo;
	
	@Autowired
	public CustomUserDetailsService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserModel user = userRepo.findByUsername(username);
		
		if(user == null)
			throw new UsernameNotFoundException("User Not Found");
		
		ArrayList<SimpleGrantedAuthority> role = new ArrayList<SimpleGrantedAuthority>();
		role.add(new SimpleGrantedAuthority(user.getRole()));
		
		User newUser = new User(user.getUsername(), user.getPassword(), role);
		System.out.println("Authority : === " + newUser.getAuthorities().toString());
		
		return newUser;
	}

}
