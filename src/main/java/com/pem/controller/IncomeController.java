package com.pem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.income.IncomeRequestDto;
import com.pem.dto.income.IncomeResponseDto;
import com.pem.service.IncomeService;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@PostMapping("/addIncome")
	public String saveIncome(@RequestBody IncomeRequestDto dto) {
		boolean saved = incomeService.saveIncome(dto);
		return saved ? "Income saved successfully" : "Failed to save income";
	}

	@GetMapping("/date-range")
	public ResponseEntity<Page<IncomeResponseDto>> getIncomeByDateRange(@RequestParam String email,
			@RequestParam String startDate, @RequestParam String endDate, @RequestParam int page,
			@RequestParam int size) {

		Page<IncomeResponseDto> pageResponse = incomeService.getFilteredIncomesBetweenDates(email, startDate, endDate,
				page, size);

		return ResponseEntity.ok(pageResponse);
	}

	@GetMapping("/filter")
	public ResponseEntity<Page<IncomeResponseDto>> getFilteredIncomes(@RequestParam String email,
			@RequestParam String filterType, @RequestParam String filterValue, @RequestParam int page,
			@RequestParam int size) {

		Page<IncomeResponseDto> filteredIncomes = incomeService.getFilteredIncomesWithPagination(email, filterType,
				filterValue, page, size);

		return ResponseEntity.ok(filteredIncomes);
	}

	@GetMapping("/allIncomes")
	public ResponseEntity<Page<IncomeResponseDto>> getAllIncome(@RequestParam String email, @RequestParam int page,
			@RequestParam int size) {

		Page<IncomeResponseDto> filteredIncomes = incomeService.getAllIncomes(email, page, size);

		return ResponseEntity.ok(filteredIncomes);
	}

	@GetMapping("/get/{id}")
	public IncomeResponseDto getById(@PathVariable Long id) {
		return incomeService.getById(id);
	}

	@PutMapping("/updateIncome")
	public ResponseEntity<String> updateIncome(@RequestBody IncomeRequestDto dto) {
		boolean updated = incomeService.update(dto);
		if (updated) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
		}
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		incomeService.delete(id);
		return "Income deleted successfully";
	}
}
