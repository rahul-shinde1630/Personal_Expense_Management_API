package com.pem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.user.LoginRequestDto;
import com.pem.dto.user.SignUpRequestDto;
import com.pem.entity.UserEntity;
import com.pem.mapper.SignupMapper;
import com.pem.repository.UserRepository;
import com.pem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SignupMapper signupMapper;

	@Override
	public SignUpRequestDto register(SignUpRequestDto dto) {

		UserEntity entity = signupMapper.toEntity(dto);

		if (userRepository.findByEmail(dto.getEmail()) != null) {
			return null; // Email already exists
		}

		UserEntity saved = userRepository.save(entity);

		return signupMapper.toDto(saved);
	}

	@Override
	public boolean validateLogin(LoginRequestDto dto) {
		UserEntity user = userRepository.findByEmail(dto.getEmail());

		if (user != null && user.getPassword().equals(dto.getPassword())) {
			return true;
		}
		return false;
	}

}
