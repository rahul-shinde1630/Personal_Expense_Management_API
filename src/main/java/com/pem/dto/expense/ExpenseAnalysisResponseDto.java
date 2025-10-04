package com.pem.dto.expense;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseAnalysisResponseDto {
	private double totalSpent;
	private String topCategory;
	private double topCategoryPercent;
	private String monthComparison;
	private Map<String, Double> categoryWisePercent;

}
