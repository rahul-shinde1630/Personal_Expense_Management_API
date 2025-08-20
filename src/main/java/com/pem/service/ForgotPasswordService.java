package com.pem.service;

import com.pem.dto.forgot.ForgotPasswordRequest;
import com.pem.dto.forgot.OtpValidationRequest;

public interface ForgotPasswordService {
	String sendOtp(ForgotPasswordRequest request);

	String validateOtpAndResetPassword(OtpValidationRequest request);
}
