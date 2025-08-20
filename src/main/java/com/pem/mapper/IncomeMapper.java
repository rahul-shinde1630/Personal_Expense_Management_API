package com.pem.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pem.dto.income.IncomeRequestDto;
import com.pem.dto.income.IncomeResponseDto;
import com.pem.entity.Bank;
import com.pem.entity.Category;
import com.pem.entity.Income;
import com.pem.entity.UserEntity;
import com.pem.repository.BankRepository;
import com.pem.repository.CategoryRepository;
import com.pem.repository.UserRepository;

@Component
public class IncomeMapper {

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	public Income toEntity(IncomeRequestDto dto) {
		Income income = new Income();

		income.setId((long) dto.getId());
		income.setTitle(dto.getTitle());
		income.setAmount(dto.getAmount());
		income.setIncomeDate(dto.getIncomeDate());

		// Existing or New Bank
		Bank bank = bankRepository.findByNameAndUser_Email(dto.getAccount(), dto.getUser());
		if (bank == null && dto.getNewAccount() != null && !dto.getNewAccount().isEmpty()) {
			bank = new Bank();
			bank.setName(dto.getNewAccount());
			bank.setUser(userRepository.findByEmail(dto.getUser()));
			bank = bankRepository.save(bank);
		}
		income.setBank(bank);

		// Existing or New Category
		Category category = categoryRepository.findByNameAndUser_Email(dto.getCategory(), dto.getUser());
		if (category == null && dto.getNewCategory() != null && !dto.getNewCategory().isEmpty()) {
			category = new Category();
			category.setName(dto.getNewCategory());
			category.setUser(userRepository.findByEmail(dto.getUser()));
			category = categoryRepository.save(category);
		}
		income.setCategory(category);

		// User
		UserEntity user = userRepository.findByEmail(dto.getUser());
		income.setUser(user);

		return income;
	}

	public IncomeResponseDto toDto(Income income) {
		IncomeResponseDto dto = new IncomeResponseDto();
		dto.setId(income.getId());
		dto.setTitle(income.getTitle());
		dto.setAmount(income.getAmount());
		dto.setIncomeDate(income.getIncomeDate());
		dto.setCategory(income.getCategory() != null ? income.getCategory().getName() : null);
		dto.setAccount(income.getBank() != null ? income.getBank().getName() : null);
		return dto;
	}

	public List<IncomeResponseDto> toDtoList(List<Income> incomes) {
		return incomes.stream().map(this::toDto).collect(Collectors.toList());
	}
}
