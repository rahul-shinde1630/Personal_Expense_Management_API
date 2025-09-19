package com.pem.dto.budget;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetPageResponseDto {
	private List<BudgetResponseDto> content;
	private int number; // current page (0-based)
	private int totalPages;
	private long totalElements;
	private int size; // page size
}
