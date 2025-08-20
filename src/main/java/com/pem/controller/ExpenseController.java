package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.pem.dto.expense.ExpenseDto;
import com.pem.dto.expense.ExpenseRequestDto;
import com.pem.dto.expense.ExpenseResponseDto;
import com.pem.dto.expense.UpdateExpenseDto;
import com.pem.service.ExpenseService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/expenses/saveExpense")
	public ResponseEntity<?> addExpense(@RequestBody ExpenseRequestDto dto) {

		boolean savedExpense = expenseService.saveExpense(dto);
		return ResponseEntity.ok(savedExpense);
	}

	@GetMapping("/date-range")
	public List<ExpenseResponseDto> getExpensesByDateRange(@RequestParam String email, @RequestParam String startDate,
			@RequestParam String endDate) {

		return expenseService.getExpensesBetweenDates(email, startDate, endDate);
	}

	@GetMapping("expenses/{id}")
	public ExpenseDto getExpenseById(@PathVariable int id, @RequestParam String email) {
		return expenseService.getExpenseById(id, email);

	}

	@GetMapping("/expenses/filter")
	public ResponseEntity<Page<ExpenseResponseDto>> getFilteredExpenses(@RequestParam String email,
			@RequestParam String filterType, @RequestParam String filterValue, @RequestParam int page,
			@RequestParam int size) {

		Page<ExpenseResponseDto> filtered = expenseService.getFilteredExpensesWithPagination(email, filterType,
				filterValue, page, size);
		return ResponseEntity.ok(filtered);
	}

	@PutMapping("/expenses/updateExpense")
	public ResponseEntity<?> updateExpense(@RequestBody UpdateExpenseDto dto) {
		boolean updated = expenseService.updateExpense(dto);
		if (updated) {
			return ResponseEntity.ok("Expense updated successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found for user.");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable int id) {
		boolean deleted = expenseService.deleteExpense(id);
		if (deleted) {
			return ResponseEntity.ok("Deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
		}
	}

}
