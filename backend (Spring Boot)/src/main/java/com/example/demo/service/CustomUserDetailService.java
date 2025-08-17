package com.example.demo.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AuthenticationEntity;
import com.example.demo.repo.AuthRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	 private final AuthRepo auth;

	
	    public CustomUserDetailService(AuthRepo auth) {
	        this.auth = auth;
	    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthenticationEntity users = auth.findByUsername(username).orElseThrow( ()-> new UsernameNotFoundException("User not found"));
		
		return new User(users.getUsername(),users.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
	}
	
	
}
