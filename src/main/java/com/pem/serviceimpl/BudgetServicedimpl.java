package com.pem.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.budget.BudgetRequestDto;
import com.pem.dto.budget.BudgetResponseDto;
import com.pem.entity.Budget;
import com.pem.mapper.BudgetMapper;
import com.pem.repository.BudgetRepository;
import com.pem.service.BudgetService;

@Service
public class BudgetServicedimpl implements BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private BudgetMapper budgetMapper;

	@Override
	public boolean saveBudget(BudgetRequestDto dto) {
		try {
			Budget budget = budgetMapper.toEntity(dto);
			budgetRepository.save(budget);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Budget> getBudgetsByUserId(String email) {
		return budgetRepository.findByUserEmailAndIsDeletedFalse(email);
	}

	@Override
	public BudgetResponseDto getBudgetById(Long id) {
		try {
			Budget budget = budgetRepository.findById(id).orElse(null);
			if (budget != null) {
				return budgetMapper.toDto(budget);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateBudget(BudgetRequestDto dto) {
		try {

			Budget existing = budgetRepository.findById(dto.getId()).orElse(null);
			if (existing != null) {
				budgetMapper.updateEntity(existing, dto);
				budgetRepository.save(existing);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBudget(Long id) {
		Optional<Budget> opt = budgetRepository.findById(id);

		if (opt.isPresent()) {
			Budget entity = opt.get();
			entity.setDeleted(true);
			budgetRepository.save(entity);
			return true;
		} else {
			System.out.println("LentMoney not found with id: " + id);
			return false;
		}
	}

}
