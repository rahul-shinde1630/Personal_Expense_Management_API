package com.pem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.pem.dto.expense.ExpenseDto;
import com.pem.dto.expense.ExpenseRequestDto;
import com.pem.dto.expense.ExpenseResponseDto;
import com.pem.dto.expense.UpdateExpenseDto;
import com.pem.entity.Expense;

public interface ExpenseService {
	Expense saveExpense(ExpenseRequestDto dto);

	ExpenseDto getExpenseById(int id, String email);

	boolean updateExpense(UpdateExpenseDto dto);

	boolean deleteExpense(int id);

	List<ExpenseResponseDto> getExpensesBetweenDates(String email, String startDate, String endDate);

	Page<ExpenseResponseDto> getFilteredExpensesWithPagination(String email, String filterType, String filterValue,
			int page, int size);

	Object analyzeSpending(String email, int month, int year);

	List<ExpenseResponseDto> getExpensesByDate(LocalDate localDate);
}
