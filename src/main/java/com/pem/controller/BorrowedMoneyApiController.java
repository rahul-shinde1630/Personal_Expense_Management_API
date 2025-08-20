package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.borrowedmoney.BorrowedMoneyRequestDto;
import com.pem.dto.borrowedmoney.BorrowedMoneyResponseDto;
import com.pem.service.BorrowedMoneyService;

@RestController
@RequestMapping("/api/borrow")
@CrossOrigin("*")
public class BorrowedMoneyApiController {

	@Autowired
	private BorrowedMoneyService service;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody BorrowedMoneyRequestDto dto) {
		boolean result = service.addBorrow(dto);
		if (result) {
			return ResponseEntity.ok("Saved");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save");
		}
	}

	@GetMapping("/user/{email}")
	public ResponseEntity<List<BorrowedMoneyResponseDto>> getAll(@PathVariable String email) {
		List<BorrowedMoneyResponseDto> list = service.getAllBorrows(email);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BorrowedMoneyResponseDto> getById(@PathVariable Long id) {
		BorrowedMoneyResponseDto dto = service.getById(id);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Update
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody BorrowedMoneyRequestDto dto) {
		boolean result = service.update(dto);
		if (result) {
			return ResponseEntity.ok("Updated");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
		}
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		boolean result = service.delete(id);
		if (result) {
			return ResponseEntity.ok("Deleted");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete");
		}
	}
}
