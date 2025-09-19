package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// ✅ Save Budget
	@PostMapping("/save")
	public ResponseEntity<String> saveBudget(@RequestBody BudgetRequestDto dto) {
		boolean isSaved = budgetService.saveBudget(dto);
		if (isSaved) {
			return ResponseEntity.ok("Budget saved successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to save budget.");
		}
	}

	// ✅ Get all budgets for user
	@GetMapping("/user/{email}")
	public ResponseEntity<List<Budget>> getBudgetsByUser(@PathVariable String email) {
		List<Budget> budgets = budgetService.getBudgetsByUserId(email);
		return ResponseEntity.ok(budgets);
	}

	// ✅ Get distinct categories for user
	@GetMapping("/user/{email}/categories")
	public ResponseEntity<List<String>> getCategoriesByUser(@PathVariable String email) {
		List<String> categories = budgetService.getCategoriesByUser(email);
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<BudgetResponseDto>> filterBudgets(@RequestParam String email,
			@RequestParam(required = false) String category, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {

		int pageIndex = (page > 0) ? page - 1 : 0;
		int pageSize = (size > 0) ? size : 10;

		Page<BudgetResponseDto> budgetPage = budgetService.filterBudgets(email, category, pageIndex, pageSize);

		return ResponseEntity.ok(budgetPage.getContent()); // ✅ returns array-like JSON
	}

	// ✅ Get Budget by ID
	@GetMapping("/{id}")
	public ResponseEntity<BudgetResponseDto> getBudgetById(@PathVariable Long id) {
		BudgetResponseDto dto = budgetService.getBudgetById(id);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// ✅ Update Budget
	@PutMapping("/update")
	public ResponseEntity<String> updateBudget(@RequestBody BudgetRequestDto dto) {
		boolean result = budgetService.updateBudget(dto);
		if (result) {
			return ResponseEntity.ok("Budget updated successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to update budget.");
		}
	}

	// ✅ Delete Budget
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
