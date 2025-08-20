package com.pem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pem.dto.budget.BudgetRequestDto;
import com.pem.dto.budget.BudgetResponseDto;
import com.pem.entity.Budget;
import com.pem.entity.UserEntity;
import com.pem.repository.UserRepository;

@Component
public class BudgetMapper {

	@Autowired
	private UserRepository userRepository;

	public Budget toEntity(BudgetRequestDto dto) {
		Budget budget = new Budget();
		budget.setTitle(dto.getTitle());
		budget.setAmount(dto.getAmount());
		budget.setCategory(dto.getCategory());
		budget.setFromDate(dto.getFromDate());
		budget.setToDate(dto.getToDate());

		if (dto.getUser() != null) {
			UserEntity user = userRepository.findByEmail(dto.getUser());
			budget.setUser(user);
		}

		return budget;
	}

	public BudgetResponseDto toDto(Budget budget) {
		return new BudgetResponseDto(budget.getId(), budget.getTitle(), budget.getAmount(), budget.getCategory(),
				budget.getFromDate(), budget.getToDate());
	}

	public void updateEntity(Budget existing, BudgetRequestDto dto) {
		existing.setTitle(dto.getTitle());
		existing.setAmount(dto.getAmount());
		existing.setCategory(dto.getCategory());
		existing.setFromDate(dto.getFromDate());
		existing.setToDate(dto.getToDate());
	}
}
