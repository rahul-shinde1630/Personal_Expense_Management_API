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

	@PostMapping("/send-otp")
	public String sendOtp(@RequestBody ForgotPasswordRequest request) {
		return forgotPasswordService.sendOtp(request);
	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestBody OtpValidationRequest request) {
		return forgotPasswordService.validateOtpAndResetPassword(request);
	}
}
