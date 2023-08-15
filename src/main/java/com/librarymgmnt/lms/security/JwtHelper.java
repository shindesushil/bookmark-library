package com.librarymgmnt.lms.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date jwtExpire = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRE);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(jwtExpire)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET_KEY)
				.compact()
			;
		
		return token;
	}
	
	public String getUsernameFromJwt(String token) {
		Claims claim = Jwts.parser()
				.setSigningKey(SecurityConstants.JWT_SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
			;
		
		return claim.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET_KEY).parse(token);
			return true;
		} catch(Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT Expired or Invalid!");
		}
	}
	
}
