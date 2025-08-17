package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config;
import com.example.demo.JWtutil;
import com.example.demo.securityconfig;
import com.example.demo.Entity.AuthenticationEntity;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:8080")
public class AuthController {

	
	@Autowired
	private AuthService auth;
	
	  @Autowired
	  private DaoAuthenticationProvider authentication;

	  @Autowired
	  private Config config;
	  
	  @Autowired
	  private JWtutil jwtutil;
	  
	@PostMapping("/signup")
	public String saveuserandpass(@RequestBody AuthenticationEntity auther) {
	return auth.saveuserandpass(auther);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> logincheck(@RequestBody AuthenticationEntity auther) {
		
		try {
	        org.springframework.security.core.Authentication auth = authentication.authenticate(
	            new UsernamePasswordAuthenticationToken(auther.getUsername(), auther.getPassword()));

	        if (auth.isAuthenticated()) {
	        	String token = jwtutil.generateJWTtoken(auther.getUsername());
	            return ResponseEntity.ok(Map.of("token",token));
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Invalid username or password","error"));
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Login failed","error"));
	}
	
	@GetMapping("/admin")
	public String adminPanel() {	
		return "Welcome to admin panel";
	}
	
	@GetMapping("/dashboard")
	public String userpanel() {
		return "Welcome to user panel";
	}
	
}
