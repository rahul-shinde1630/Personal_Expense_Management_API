package com.pem.mapper;

import org.springframework.stereotype.Component;

import com.pem.dto.user.LoginRequestDto;
import com.pem.entity.UserEntity;

@Component
public class LoginMapper {

	// DTO to Entity
	public UserEntity toEntity(LoginRequestDto dto) {
		UserEntity user = new UserEntity();
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return user;
	}

	// Entity to DTO
	public static LoginRequestDto toDto(UserEntity entity) {
		LoginRequestDto dto = new LoginRequestDto();
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		return dto;
	}
}
