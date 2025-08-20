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

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private OtpVerificationRepository otpRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public String sendOtp(ForgotPasswordRequest request) {
		String otp = String.format("%06d", new Random().nextInt(999999));
		otpRepo.deleteByEmail(request.getEmail());

		OtpVerification otpEntity = new OtpVerification();
		otpEntity.setEmail(request.getEmail());
		otpEntity.setOtp(otp);
		otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
		otpRepo.save(otpEntity);

		emailService.sendEmail(request.getEmail(), "OTP for Password Reset", "Your OTP is: " + otp);
		return "OTP sent successfully to " + request.getEmail();
	}

	@Override
	public String validateOtpAndResetPassword(OtpValidationRequest request) {
		OtpVerification otpEntity = otpRepo.findByEmailAndOtp(request.getEmail(), request.getOtp())
				.orElseThrow(() -> new RuntimeException("Invalid OTP"));

		if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP Expired");
		}
		UserEntity user = userRepo.findByEmail(request.getEmail());

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		user.setPassword(request.getNewPassword());
		userRepo.save(user);

		otpRepo.deleteByEmail(request.getEmail());
		return "Password reset successful";
	}
}