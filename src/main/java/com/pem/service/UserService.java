package com.pem.service;

import com.pem.dto.user.LoginRequestDto;
import com.pem.dto.user.SignUpRequestDto;

public interface UserService {
	SignUpRequestDto register(SignUpRequestDto dto);

	boolean validateLogin(LoginRequestDto dto);
}
