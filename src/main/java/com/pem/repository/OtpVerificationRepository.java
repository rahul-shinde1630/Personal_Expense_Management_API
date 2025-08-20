package com.pem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.pem.entity.OtpVerification;

import jakarta.transaction.Transactional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
	Optional<OtpVerification> findByEmailAndOtp(String email, String otp);

	@Modifying
	@Transactional
	void deleteByEmail(String email);
}
