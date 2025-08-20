package com.pem.mapper;

import org.springframework.stereotype.Component;

import com.pem.dto.expense.ExpenseDto;
import com.pem.entity.Expense;

@Component
public class EditMapper {
	public ExpenseDto toDTO(Expense expense) {
		// Convert to DTO
		ExpenseDto dto = new ExpenseDto();
		dto.setId(expense.getId());
		dto.setCategory(expense.getCategory().getName());
		dto.setAccount(expense.getAccount().getName());
		dto.setAmount(expense.getAmount());
		dto.setPaymentMode(expense.getPaymentMode());
		dto.setExpenseDate(expense.getExpenseDate());
		dto.setExpenseTime(expense.getExpenseTime());
		return dto;
	}
}
