package com.pem.mapper;

import org.springframework.stereotype.Component;

import com.pem.dto.user.LoginRequestDto;
import com.pem.dto.user.SignUpRequestDto;
import com.pem.entity.UserEntity;

@Component
public class SignupMapper {

	public UserEntity toEntity(SignUpRequestDto dto) {
		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setCountry(dto.getCountry());
		entity.setCheckbox(dto.isCheckbox());
		return entity;
	}

	public SignUpRequestDto toDto(UserEntity entity) {
		return new SignUpRequestDto(entity.getName(), entity.getEmail(), entity.getPassword(), entity.getCountry(),
				entity.isCheckbox());
	}

	public UserEntity toEntity(LoginRequestDto dto) {

		UserEntity entity = new UserEntity();
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		return null;
	}

}
