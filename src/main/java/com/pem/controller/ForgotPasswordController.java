package com.pem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.forgot.ForgotPasswordRequest;
import com.pem.dto.forgot.OtpValidationRequest;
import com.pem.service.ForgotPasswordService;

@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	// Step 1: Send OTP to user's email
	@PostMapping("/send-otp")
	public String sendOtp(@RequestBody ForgotPasswordRequest request) {
		return forgotPasswordService.sendOtp(request);
	}

	// Step 2: Verify the OTP (returns boolean)
	@PostMapping("/verify-otp")
	public boolean verifyOtp(@RequestBody OtpValidationRequest request) {
		return forgotPasswordService.verifyOtp(request);
	}

	// Step 3: Reset password after successful OTP verification
	@PostMapping("/reset-password")
	public String resetPassword(@RequestBody OtpValidationRequest request) {
		return forgotPasswordService.resetPassword(request);
	}
}
