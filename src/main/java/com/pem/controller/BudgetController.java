package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.budget.BudgetRequestDto;
import com.pem.dto.budget.BudgetResponseDto;
import com.pem.entity.Budget;
import com.pem.service.BudgetService;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "*")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@PostMapping("/save")
	public ResponseEntity<String> saveBudget(@RequestBody BudgetRequestDto dto) {
		boolean isSaved = budgetService.saveBudget(dto);
		if (isSaved) {
			return ResponseEntity.ok("Budget saved successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to save budget.");
		}
	}

	@GetMapping("/user/{email}")
	public ResponseEntity<List<Budget>> getBudgetsByUser(@PathVariable String email) {
		List<Budget> budgets = budgetService.getBudgetsByUserId(email);
		return ResponseEntity.ok(budgets);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetResponseDto> getBudgetById(@PathVariable Long id) {
		BudgetResponseDto dto = budgetService.getBudgetById(id);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Update Budget
	@PutMapping("/update")
	public ResponseEntity<String> updateBudget(@RequestBody BudgetRequestDto dto) {
		boolean result = budgetService.updateBudget(dto);
		if (result) {
			return ResponseEntity.ok("Budget updated successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to update budget.");
		}
	}

	// Delete Budget
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
		boolean result = budgetService.deleteBudget(id);
		if (result) {
			return ResponseEntity.ok("Budget deleted successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete budget.");
		}
	}
}
