package com.pem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.user.LoginRequestDto;
import com.pem.dto.user.SignUpRequestDto;
import com.pem.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDto dto) {
		SignUpRequestDto result = userService.register(dto);

		if (result == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/login")
	public boolean login(@RequestBody LoginRequestDto dto) {
		return userService.validateLogin(dto);
	}
}
