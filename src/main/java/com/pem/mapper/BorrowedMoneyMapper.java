package com.pem.mapper;

import java.util.List;
import java.util.stream.Collectors;

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
		dto.setStatus(b.getStatus().name());
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
		dto.setStatus(entity.getStatus().name());
		return dto;
	}

	public List<BorrowedMoneyResponseDto> toResponseDto(List<BorrowedMoney> entities) {
		return entities.stream().map(this::toResponseDto).collect(Collectors.toList());
	}

}
