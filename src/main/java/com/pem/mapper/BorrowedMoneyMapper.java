package com.pem.mapper;

import org.springframework.stereotype.Component;

import com.pem.dto.borrowedmoney.BorrowedMoneyResponseDto;
import com.pem.entity.BorrowedMoney;

@Component
public class BorrowedMoneyMapper {

	public BorrowedMoneyResponseDto toDto(BorrowedMoney b) {
		BorrowedMoneyResponseDto dto = new BorrowedMoneyResponseDto();
		dto.setId(b.getId());
		dto.setPersonName(b.getPersonName());
		dto.setAmount(b.getAmount());
		dto.setBorrowedDate(b.getBorrowedDate());
		dto.setDueDate(b.getDueDate());
		dto.setReason(b.getReason());
		dto.setRemainingAmount(b.getRemainingAmount());
		dto.setStatus(b.getStatus().toString());
		return dto;
	}

	public BorrowedMoneyResponseDto toResponseDto(BorrowedMoney entity) {
		BorrowedMoneyResponseDto dto = new BorrowedMoneyResponseDto();
		dto.setId(entity.getId());
		dto.setPersonName(entity.getPersonName());
		dto.setAmount(entity.getAmount());
		dto.setBorrowedDate(entity.getBorrowedDate());
		dto.setDueDate(entity.getDueDate());
		dto.setReason(entity.getReason());
		dto.setRemainingAmount(entity.getRemainingAmount());
		return dto;
	}

}
