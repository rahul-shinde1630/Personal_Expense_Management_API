package com.pem.mapper;

import org.springframework.stereotype.Component;

import com.pem.dto.expense.UpdateExpenseDto;
import com.pem.entity.Expense;

@Component
public class UpdateMapper {

	public static Expense mapDtoToEntity(UpdateExpenseDto dto) {
		Expense expense = new Expense();
		expense.setExpenseDate(dto.getExpenseDate());
		expense.setExpenseTime(dto.getExpenseTime());
		return expense;

	}
}
