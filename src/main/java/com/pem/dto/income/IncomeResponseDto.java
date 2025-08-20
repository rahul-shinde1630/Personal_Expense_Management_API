package com.pem.dto.income;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponseDto {
	private Long id;
	private String title;
	private double amount;
	private String account;
	private String category;
	private LocalDate incomeDate;
	private String user;
}
