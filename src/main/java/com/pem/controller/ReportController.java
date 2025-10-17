package com.pem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pem.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/income")
	public Double getIncome(@RequestParam String email, @RequestParam int fromYear, @RequestParam int toYear) {
		return reportService.getTotalIncomeBetweenYears(email, fromYear, toYear);
	}

	@GetMapping("/expense")
	public Double getExpense(@RequestParam String email, @RequestParam int fromYear, @RequestParam int toYear) {
		return reportService.getTotalExpenseBetweenYears(email, fromYear, toYear);
	}

	@GetMapping("/borrowedRemaining")
	public Double getBorrowedRemaining(@RequestParam String email, @RequestParam int fromYear,
			@RequestParam int toYear) {
		return reportService.getBorrowedRemainingBetweenYears(email, fromYear, toYear);
	}
}
