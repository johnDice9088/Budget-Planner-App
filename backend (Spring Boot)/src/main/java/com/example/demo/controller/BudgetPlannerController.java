package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.BudgetPlanner;
import com.example.demo.service.MoneyBudgetService;
@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "http://localhost:8080")
public class BudgetPlannerController {

    @Autowired
    private MoneyBudgetService moneyBudgetService;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public BudgetPlanner saveBudget(@RequestBody BudgetPlanner budgetPlan) {
//        BudgetPlanner savedBudget = moneyBudgetService.saveBudget(budgetPlan);
  
    	
//    	budgetPlan.setAmount();
        return moneyBudgetService.saveBudget(budgetPlan);
    }
    
    @GetMapping("/getall")
    public List<BudgetPlanner> getAll() {
    	return moneyBudgetService.getAll();
    			}


	@PutMapping("/update/{id}")
	public BudgetPlanner updateAll(@PathVariable Long id,@RequestBody BudgetPlanner budgetPlan) {
		return moneyBudgetService.fullUpdatefields(id,budgetPlan);
	}
	
	@PutMapping("/updateone/{id}")
	public BudgetPlanner updateOne(@PathVariable Long id,@RequestBody BudgetPlanner budgetPlan) {
		return moneyBudgetService.updateOne(id, budgetPlan);
	}
	
	@DeleteMapping("/delete")
	public String deleteBudget() {
		moneyBudgetService.deleteAll();
		return "All records deleted successfully"; 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable Long id) {
		moneyBudgetService.deleteOne(id);
		return ResponseEntity.ok("Successfully deleted");
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<BudgetPlanner>> searchIncome(@RequestParam("query") String query){
		List<BudgetPlanner> result = moneyBudgetService.search(query);
		return ResponseEntity.ok(result);
		
	}
	
	//Authentication//
}