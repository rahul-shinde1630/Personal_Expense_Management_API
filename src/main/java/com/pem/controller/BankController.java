package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.bank.BankDto;
import com.pem.dto.bank.BankRequestDto;
import com.pem.service.BankService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping("/addBank")
	public ResponseEntity<?> addBank(@RequestBody BankRequestDto dto) {
		boolean savedBank = bankService.saveBank(dto);
		return ResponseEntity.ok(savedBank);
	}

	@GetMapping("/banks")
	public List<BankDto> getBanks(@RequestParam String user) {
		return bankService.getAllBanks(user);
	}

}
