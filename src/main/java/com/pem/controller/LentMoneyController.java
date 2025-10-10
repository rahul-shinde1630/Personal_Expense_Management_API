package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pem.dto.lentmoney.LentMoneyRequestDto;
import com.pem.dto.lentmoney.LentMoneyResponseDto;
import com.pem.service.LentMoneyService;

@RestController
@RequestMapping("/api/lentmoney")
@CrossOrigin
public class LentMoneyController {

	@Autowired
	private LentMoneyService service;

	@PostMapping
	public boolean save(@RequestBody LentMoneyRequestDto dto) {
		return service.save(dto);
	}

	@GetMapping("/user")
	public List<LentMoneyResponseDto> getByEmail(@RequestParam String email) {
		return service.getByEmail(email);
	}

	@DeleteMapping("/{lentId}")
	public boolean delete(@PathVariable Long lentId) {
		return service.delete(lentId);
	}

	@GetMapping("/{lentId}")
	public LentMoneyRequestDto getById(@PathVariable Long lentId) {
		return service.getById(lentId);
	}

	@PutMapping("/update")
	public boolean update(@RequestBody LentMoneyRequestDto dto) {
		return service.update(dto);
	}
}
