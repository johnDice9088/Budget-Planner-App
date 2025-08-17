package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.BudgetPlanner;

public interface BudgetPlannerRepo extends JpaRepository<BudgetPlanner, Long> {
	List<BudgetPlanner> findByCategoryContainingIgnoreCase(String category);

}
