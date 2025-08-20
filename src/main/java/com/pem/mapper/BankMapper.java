package com.pem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pem.dto.bank.BankDto;
import com.pem.dto.bank.BankRequestDto;
import com.pem.entity.Bank;
import com.pem.entity.UserEntity;
import com.pem.repository.UserRepository;

@Component
public class BankMapper {
	@Autowired
	UserRepository userRepository;

	public Bank toEntity(BankRequestDto dto) {
		Bank bank = new Bank();
		bank.setName(dto.getName());
		// Convert email -> UserEntity
		if (dto.getUser() != null) {
			UserEntity user = userRepository.findByEmail(dto.getUser());
			bank.setUser(user);
		}
		return bank;
	}

	public BankDto toDto(Bank bank) {
		BankDto dto = new BankDto();
		dto.setName(bank.getName());

		return dto;
	}

}
