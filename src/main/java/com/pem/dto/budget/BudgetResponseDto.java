package com.pem.dto.budget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponseDto {
	private Long id;
	private String title;
	private double amount;
	private String category;
	private String fromDate;
	private String toDate;

}
