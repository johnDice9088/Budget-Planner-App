package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.AuthenticationEntity;

public interface AuthRepo extends JpaRepository<AuthenticationEntity,Long>{
	
	Optional<AuthenticationEntity> findByUsername(String username);
}
