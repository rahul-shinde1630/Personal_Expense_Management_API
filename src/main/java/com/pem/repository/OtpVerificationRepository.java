package com.pem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pem.entity.OtpVerification;
import com.pem.entity.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {

	@Modifying
	@Transactional
	@Query("DELETE FROM OtpVerification o WHERE o.user = :user")
	void deleteByUser(@Param("user") UserEntity user);

	Optional<OtpVerification> findByUserAndOtp(UserEntity user, String otp);
}
