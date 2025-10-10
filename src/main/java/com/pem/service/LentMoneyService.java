package com.pem.service;

import java.util.List;

import com.pem.dto.lentmoney.LentMoneyRequestDto;
import com.pem.dto.lentmoney.LentMoneyResponseDto;

public interface LentMoneyService {
	boolean save(LentMoneyRequestDto dto);

	List<LentMoneyResponseDto> getByEmail(String email);

	boolean delete(Long lendId);

	LentMoneyRequestDto getById(Long lentId);

	boolean update(LentMoneyRequestDto dto);

	List<LentMoneyResponseDto> getUnpaidByEmail(String email);
}
