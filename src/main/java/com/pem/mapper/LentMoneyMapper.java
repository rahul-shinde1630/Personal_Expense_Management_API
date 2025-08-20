package com.pem.mapper;

import com.pem.dto.lentmoney.LentMoneyRequestDto;
import com.pem.dto.lentmoney.LentMoneyResponseDto;
import com.pem.entity.LentMoney;

public class LentMoneyMapper {

	public static LentMoney toEntity(LentMoneyRequestDto dto) {
		LentMoney entity = new LentMoney();
		entity.setLentId(dto.getLentId());
		entity.setPersonName(dto.getPersonName());
		entity.setAmount(dto.getAmount());
		entity.setLentDate(dto.getLentDate());
		entity.setDueDate(dto.getDueDate());
		entity.setReason(dto.getReason());
		entity.setRemainingAmount(dto.getRemainingAmount());
		entity.setStatus(LentMoney.Status.valueOf(dto.getStatus().toUpperCase()));
		return entity;
	}

	public static LentMoneyResponseDto toDto(LentMoney entity) {
		return new LentMoneyResponseDto(entity.getLentId(), entity.getPersonName(), entity.getAmount(),
				entity.getLentDate(), entity.getDueDate(), entity.getReason(), entity.getRemainingAmount(),
				entity.getStatus().name());
	}

	public static LentMoneyRequestDto toRequestDto(LentMoney entity) {
		LentMoneyRequestDto dto = new LentMoneyRequestDto();
		dto.setLentId(entity.getLentId());
		dto.setPersonName(entity.getPersonName());
		dto.setAmount(entity.getAmount());
		dto.setLentDate(entity.getLentDate());
		dto.setDueDate(entity.getDueDate());
		dto.setReason(entity.getReason());
		dto.setRemainingAmount(entity.getRemainingAmount());
		dto.setStatus(entity.getStatus().name());
		return dto;
	}

}
