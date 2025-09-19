package com.pem.dto.budget;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetRequestDto {
	private Long id;

	private String title;
	private double amount;
	private String category;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String user;
}
