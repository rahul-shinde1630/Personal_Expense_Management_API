package com.pem.serviceimpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.forgot.ForgotPasswordRequest;
import com.pem.dto.forgot.OtpValidationRequest;
import com.pem.entity.OtpVerification;
import com.pem.entity.UserEntity;
import com.pem.repository.OtpVerificationRepository;
import com.pem.repository.UserRepository;
import com.pem.service.EmailService;
import com.pem.service.ForgotPasswordService;

import jakarta.transaction.Transactional;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private OtpVerificationRepository otpRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailService emailService;

	@Override
	@Transactional
	public String sendOtp(ForgotPasswordRequest request) {
		UserEntity user = userRepo.findByEmail(request.getEmail());
		if (user == null) {
			throw new RuntimeException("No user found with email: " + request.getEmail());
		}

		otpRepo.deleteByUser(user); // Delete old OTPs

		String otp = String.format("%06d", new Random().nextInt(999999));

		OtpVerification otpEntity = new OtpVerification();
		otpEntity.setUser(user);
		otpEntity.setOtp(otp);
		otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));

		otpRepo.save(otpEntity);

		emailService.sendEmail(request.getEmail(), "OTP for Password Reset", "Your OTP is: " + otp);
		return "OTP sent successfully to " + request.getEmail();
	}

	@Override
	public boolean verifyOtp(OtpValidationRequest request) {
		// Trim and normalize email
		String email = request.getEmail().trim().toLowerCase();

		// Fetch user safely
		UserEntity user = userRepo.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found with email: " + email);
		}

		// Fetch OTP
		OtpVerification otpEntity = otpRepo.findByUserAndOtp(user, request.getOtp())
				.orElseThrow(() -> new RuntimeException("Invalid OTP"));

		// Check expiry
		if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP Expired");
		}

		// OTP is valid
		return true;
	}

	@Override
	@Transactional
	public String resetPassword(OtpValidationRequest request) {
		if (!request.getNewPassword().equals(request.getConfirmPassword())) {
			throw new RuntimeException("Passwords do not match");
		}

		UserEntity user = userRepo.findByEmail(request.getEmail());
		if (user == null) {
			throw new RuntimeException("User not found");
		}

		user.setPassword(request.getNewPassword());
		userRepo.save(user);

		otpRepo.deleteByUser(user); // Remove OTPs after reset
		return "Password reset successfully!";
	}
}
