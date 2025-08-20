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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.repayment.RepaymentRequestDto;
import com.pem.dto.repayment.RepaymentResponseDto;
import com.pem.service.RepaymentService;

@RestController
@RequestMapping("/api/repayments")
@CrossOrigin(origins = "*")
public class RepaymentController {

	@Autowired
	private RepaymentService repaymentService;

	@PostMapping("/add")
	public ResponseEntity<String> addRepayment(@RequestBody RepaymentRequestDto dto) {
		repaymentService.addRepayment(dto);
		return ResponseEntity.ok("Repayment added successfully");
	}

	@GetMapping("/{id}")
	public ResponseEntity<RepaymentResponseDto> getRepaymentById(@PathVariable Long id) {
		RepaymentResponseDto dto = repaymentService.getRepaymentById(id);
		return ResponseEntity.ok(dto);
	}

	@PutMapping
	public ResponseEntity<String> updateRepayment(@RequestBody RepaymentRequestDto dto) {
		repaymentService.updateRepayment(dto);
		return ResponseEntity.ok("Repayment updated successfully");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRepayment(@PathVariable Long id) {
		repaymentService.deleteById(id);
		return ResponseEntity.ok("Repayment deleted successfully");
	}

	@GetMapping("/filter")
	public List<RepaymentResponseDto> getFilteredRepayments(@RequestParam String email,
			@RequestParam String repaymentType, @RequestParam(required = false) String filterValue) {

		return repaymentService.getFilteredRepayments(email, repaymentType, filterValue);
	}

	@GetMapping("/data/{email}")
	public List<RepaymentResponseDto> getRepayments(@PathVariable String email) {
		return repaymentService.getRepaymentsByEmail(email);
	}

}
