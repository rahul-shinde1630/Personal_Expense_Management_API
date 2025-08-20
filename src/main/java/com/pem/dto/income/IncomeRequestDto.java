package com.pem.dto.income;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequestDto {
	private int id;
	private String account;
	private String newAccount;
	private String title;
	private double amount;
	private String category;
	private String newCategory;
	private LocalDate incomeDate;
	private String user;
}
