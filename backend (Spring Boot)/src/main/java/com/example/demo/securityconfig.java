package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.CustomUserDetailService;

import jakarta.servlet.http.HttpServlet;

@Configuration
@EnableWebSecurity
public class securityconfig{
	
	@Autowired
	private Config config;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JWTFilter jwtfilter;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(customUserDetailService); 
		auth.setPasswordEncoder(config.passwordhashing());

		return auth;
	}
	 	
	
	@Bean
	public AuthenticationManager authenicationManager() {
		return new ProviderManager(List.of(authenticationProvider()));
	}
	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authz ->  authz
		.requestMatchers("/api/admin/**").hasRole("ADMIN")
		.requestMatchers("/api/user").authenticated()
		.requestMatchers("/api/signup").permitAll()
		.requestMatchers("/api/login").permitAll()
		.requestMatchers("/api/budget/**").authenticated()
		
		.anyRequest().permitAll()
		
		
		)
		
		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class); 
//		.formLogin(form -> form.permitAll().defaultSuccessUrl("/api/dashboard"));
		
		return http.build();
		
	}
	
}

