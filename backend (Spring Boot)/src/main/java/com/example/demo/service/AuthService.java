package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Entity.AuthenticationEntity;
import com.example.demo.repo.AuthRepo;

@Service
public class AuthService {

	
	@Autowired
	private AuthRepo auth ;
	
	@Autowired
	private BCryptPasswordEncoder pass;
		
		public String saveuserandpass(AuthenticationEntity auther) {
			 auther.setPassword(pass.encode(auther.getPassword()));
			 auth.save(auther);
			 return "Credientials saved successfully";
		}
	
//	public String loginchecker(AuthenticationEntity auther)
//	{
//		List<AuthenticationEntity> getall = auth.findAll();
//		for(AuthenticationEntity users: getall) {
//			if(users.getUsername().equals(auther.getUsername())  &&  pass.matches(auther.getPassword(), users.getPassword()) ) {
//				
//				return "Login successful";
//			}
//			
//		}
//		return "Login failed";
//	}
	
}
