package com.pem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pem.dto.expense.ExpenseDto;
import com.pem.dto.expense.ExpenseRequestDto;
import com.pem.dto.expense.ExpenseResponseDto;
import com.pem.dto.expense.UpdateExpenseDto;

public interface ExpenseService {
	boolean saveExpense(ExpenseRequestDto dto);

	ExpenseDto getExpenseById(int id, String email);

	boolean updateExpense(UpdateExpenseDto dto);

	boolean deleteExpense(int id);

	List<ExpenseResponseDto> getExpensesBetweenDates(String email, String startDate, String endDate);

	Page<ExpenseResponseDto> getFilteredExpensesWithPagination(String email, String filterType, String filterValue,
			int page, int size);
}
