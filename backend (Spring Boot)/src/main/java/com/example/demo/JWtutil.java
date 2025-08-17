package com.example.demo;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWtutil {
	private static final String secret ="54L0OhEUcLMSbM5v5YxnjaYolM1y3ixe";
	
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
	
	
	public String generateJWTtoken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.signWith(SECRET_KEY,Jwts.SIG.HS256)
				.compact();
	}
	
	public boolean validToken(String token, String dbuser) {
	    try {
//	        String username = extractUsername(token);
	        return token.equals(dbuser);
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	
	public String extractUsername(String token) {
	    try {
	        return Jwts.parser()
	            .verifyWith(SECRET_KEY)
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	    } catch (Exception e) {
	        throw new RuntimeException("Invalid JWT token: " + e.getMessage());
	    }
}
	}
