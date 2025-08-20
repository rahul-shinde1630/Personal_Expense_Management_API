package com.pem.service;

import org.springframework.data.domain.Page;

import com.pem.dto.income.IncomeRequestDto;
import com.pem.dto.income.IncomeResponseDto;

public interface IncomeService {

	boolean saveIncome(IncomeRequestDto dto);

	boolean delete(Long id);

	IncomeResponseDto getById(Long id);

	boolean update(IncomeRequestDto dto);

	Page<IncomeResponseDto> getFilteredIncomesWithPagination(String email, String filterType, String filterValue,
			int page, int size);

	Page<IncomeResponseDto> getAllIncomes(String email, int page, int size);

	Page<IncomeResponseDto> getFilteredIncomesBetweenDates(String email, String startDate, String endDate, int page,
			int size);

}
