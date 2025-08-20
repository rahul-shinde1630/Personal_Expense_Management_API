package com.pem.service;

import java.util.List;

import com.pem.dto.budget.BudgetRequestDto;
import com.pem.dto.budget.BudgetResponseDto;
import com.pem.entity.Budget;

public interface BudgetService {

	public boolean saveBudget(BudgetRequestDto dto);

	public BudgetResponseDto getBudgetById(Long id);

	public boolean updateBudget(BudgetRequestDto dto);

	public boolean deleteBudget(Long id);

	List<Budget> getBudgetsByUserId(String email);

}
