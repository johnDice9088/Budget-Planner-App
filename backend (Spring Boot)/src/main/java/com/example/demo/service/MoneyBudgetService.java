package com.example.demo.service; 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AuthenticationEntity;
import com.example.demo.Entity.BudgetPlanner;
import com.example.demo.repo.AuthRepo;
import com.example.demo.repo.BudgetPlannerRepo;

@Service
public class MoneyBudgetService {

    @Autowired
    private BudgetPlannerRepo budgetPlannerRepo;
	@Autowired
	private AuthRepo auth ;
    public BudgetPlanner saveBudget(BudgetPlanner budgetPlan) {
    	   String username = SecurityContextHolder.getContext().getAuthentication().getName();
    AuthenticationEntity user = auth.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")) ;
    System.out.println(user);
    budgetPlan.setUserId(user);
        return budgetPlannerRepo.save(budgetPlan);
    }
    public List<BudgetPlanner> getAll () {
    	return budgetPlannerRepo.findAll();
    	}
    
    public BudgetPlanner fullUpdatefields(Long id, BudgetPlanner budgetplan) {
    	BudgetPlanner existing = budgetPlannerRepo.findById(id).orElseThrow(()-> new RuntimeException("Budget not found"));
    	
    existing.setCategory(budgetplan.getCategory());
    existing.setAmount(budgetplan.getAmount());
    existing.setNote(budgetplan.getNote());
    	return budgetPlannerRepo.save(existing);
    }
    
    public BudgetPlanner updateOne(Long id,  BudgetPlanner updatedData) {
    	
    	BudgetPlanner exist = budgetPlannerRepo.findById(id).orElseThrow(()-> new RuntimeException("Budget not found for single"));
    	
    	if (updatedData.getCategory() != null) {
            exist.setCategory(updatedData.getCategory());
        }

        if (updatedData.getAmount() != null) {
            exist.setAmount(updatedData.getAmount());
        }

        if (updatedData.getNote() != null) {
            exist.setNote(updatedData.getNote());
        }
		return budgetPlannerRepo.save(exist);
    	
    }
    
    public void deleteAll(){
    	 budgetPlannerRepo.deleteAll();
    }
    
    public void deleteOne(Long id) {
    	budgetPlannerRepo.deleteById(id);
    }	
    
  public List<BudgetPlanner> search(String query){
	 return  budgetPlannerRepo.findByCategoryContainingIgnoreCase(query);
  }
} 
