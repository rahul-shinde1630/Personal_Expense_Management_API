
package com.pem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pem.dto.expense.ExpenseRequestDto;
import com.pem.dto.expense.ExpenseResponseDto;
import com.pem.dto.expense.UpdateExpenseDto;
import com.pem.entity.Bank;
import com.pem.entity.Category;
import com.pem.entity.Expense;
import com.pem.entity.UserEntity;
import com.pem.repository.BankRepository;
import com.pem.repository.CategoryRepository;
import com.pem.repository.UserRepository;

@Component
public class ExpenseMapper {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BankRepository bankRepository;

	public Expense toEntity(ExpenseRequestDto dto) {
		Expense entity = new Expense();
		entity.setAmount(dto.getAmount());
		entity.setExpenseDate(dto.getExpenseDate());
		entity.setExpenseTime(dto.getExpenseTime());

		if (dto.getUser() != null) {
			UserEntity user = userRepository.findByEmail(dto.getUser());
			entity.setUser(user);
		}

		if (dto.getCategory() != null) {
			Category category = categoryRepository.findByNameAndUser_Email(dto.getCategory(), dto.getUser());
			entity.setCategory(category);
		}

		if (dto.getAccount() != null) {
			Bank bank = bankRepository.findByNameAndUser_Email(dto.getAccount(), dto.getUser());
			entity.setAccount(bank);
		}

		entity.setPaymentMode(dto.getPaymentMode());
		return entity;
	}

	public UpdateExpenseDto toDto(Expense entity) {
		UpdateExpenseDto dto = new UpdateExpenseDto();

		dto.setAmount(entity.getAmount());
		dto.setExpenseDate(entity.getExpenseDate());
		dto.setExpenseTime(entity.getExpenseTime());
		dto.setCategory(entity.getCategory().getName());

		dto.setUser(entity.getUser().getEmail());

		return dto;
	}

	public ExpenseResponseDto toResponseDto(Expense expense) {
		ExpenseResponseDto dto = new ExpenseResponseDto();
		dto.setId(expense.getId());
		dto.setAmount(expense.getAmount());
		dto.setExpenseDate(expense.getExpenseDate().toString());
		dto.setExpenseTime(expense.getExpenseTime());
		dto.setPaymentMode(expense.getPaymentMode());
		dto.setCategory(expense.getCategory().getName());
		dto.setAccount(expense.getAccount().getName());

		return dto;
	}
}
