package com.pem.dto.expense;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDto {
	private String account;
	private String newAccount;
	private double amount;
	private String category;
	private String newCategory;
	private String paymentMode;
	private LocalDate expenseDate;
	private String expenseTime;
	private String user;
}
