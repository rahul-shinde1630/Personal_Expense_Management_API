package com.pem.dto.forgot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpValidationRequest {
	private String email;
	private String otp;
	private String newPassword;
}