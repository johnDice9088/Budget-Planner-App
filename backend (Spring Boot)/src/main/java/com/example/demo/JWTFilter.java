package com.example.demo;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.CustomUserDetailService;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	
	@Autowired
	private JWtutil jwtutil;
	
	@Autowired
	private CustomUserDetailService customer;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {	
		String token = request.getHeader("Authorization");
		
		if(token !=null && token.startsWith("Bearer ")) {
			  String OGToken = token.substring(7).trim();
			    if (!OGToken.contains(".")) {
			        throw new IllegalArgumentException("Invalid JWT format");
			    }
			String JwtUsername = jwtutil.extractUsername(OGToken);
			System.out.println(JwtUsername);
			System.out.println(OGToken);
			UserDetails user = customer.loadUserByUsername(JwtUsername);
			System.out.println(user.getUsername());
			System.out.println(jwtutil.validToken(JwtUsername,user.getUsername()));
			if(jwtutil.validToken(JwtUsername,user.getUsername())) {
				UsernamePasswordAuthenticationToken usernamepasswordauthenticationtoken =
						new UsernamePasswordAuthenticationToken(user, 
		                        null, 
		                        user.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationtoken);
			}
			
		}
		filterChain.doFilter(request, response);
	}
}
