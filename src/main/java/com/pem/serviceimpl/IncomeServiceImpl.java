package com.pem.serviceimpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pem.dto.income.IncomeRequestDto;
import com.pem.dto.income.IncomeResponseDto;
import com.pem.entity.Bank;
import com.pem.entity.Category;
import com.pem.entity.Income;
import com.pem.entity.UserEntity;
import com.pem.mapper.IncomeMapper;
import com.pem.repository.BankRepository;
import com.pem.repository.CategoryRepository;
import com.pem.repository.IncomeRepository;
import com.pem.repository.UsersRepository;
import com.pem.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private IncomeMapper incomeMapper;

	@Override
	public boolean saveIncome(IncomeRequestDto dto) {
		Income income = incomeMapper.toEntity(dto);
		incomeRepository.save(income);
		return true;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Income> optional = incomeRepository.findById(id);
		if (optional.isPresent()) {
			Income income = optional.get();
			income.setDeleted(true);
			incomeRepository.save(income);
			return true;
		} else {
			System.out.println("Income not found with id: " + id);
			return false;
		}
	}

	@Override
	public IncomeResponseDto getById(Long id) {
		Income income = incomeRepository.findById(id).orElse(null);
		if (income != null) {
			return incomeMapper.toDto(income);
		}
		return null;
	}

	@Override
	public boolean update(IncomeRequestDto dto) {
		try {
			int incomeId = (dto.getId());

			Optional<Income> optional = incomeRepository.findById(incomeId);
			Optional<UserEntity> userOptional = userRepository.findByEmail(dto.getUser());
			Optional<Bank> bankOptional = bankRepository.findByName(dto.getAccount());
			Optional<Category> categoryOptional = categoryRepository.findByName(dto.getCategory());

			if (optional.isPresent() && userOptional.isPresent() && bankOptional.isPresent()
					&& categoryOptional.isPresent()) {
				Income income = optional.get();
				UserEntity user = userOptional.get();
				Bank bank = bankOptional.get();
				Category category = categoryOptional.get();

				income.setUser(user);
				income.setBank(bank);
				income.setCategory(category);
				income.setAmount(dto.getAmount());
				income.setIncomeDate(dto.getIncomeDate());
				income.setAmount(dto.getAmount());
				income.setTitle(dto.getTitle());

				incomeRepository.save(income);
				return true;
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<IncomeResponseDto> getFilteredIncomesWithPagination(String email, String filterType, String filterValue,
			int page, int size) {
		System.out.println(filterType + "   " + filterValue);
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found with email: " + email);
		}

		UserEntity user = userOptional.get();
		int userId = user.getId();

		Pageable pageable = PageRequest.of(page, size, Sort.by("incomeDate").descending());
		Page<Income> filteredIncomes;

		LocalDate now = LocalDate.now();

		switch (filterType != null ? filterType.toLowerCase() : "") {
		case "date" -> {
			LocalDate date = LocalDate.parse(filterValue);
			filteredIncomes = incomeRepository.findByUserIdAndDateAndIsDeletedFalse(userId, date, pageable);
		}
		case "month" -> {
			int month = Integer.parseInt(filterValue);
			filteredIncomes = incomeRepository.findByUserIdAndMonthAndIsDeletedFalse(userId, month, now.getYear(),
					pageable);
		}
		case "year" -> {
			int year = Integer.parseInt(filterValue);
			filteredIncomes = incomeRepository.findByUserIdAndYearAndIsDeletedFalse(userId, year, pageable);
		}
		default -> {
			filteredIncomes = incomeRepository.findByUserIdAndIsDeletedFalse(userId, pageable);
		}
		}

		return filteredIncomes.map(incomeMapper::toDto);
	}

	@Override
	public Page<IncomeResponseDto> getAllIncomes(String email, int page, int size) {
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found with email: " + email);
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("incomeDate").descending());
		return incomeRepository.findByUserAndIsDeletedFalse(userOptional.get(), pageable).map(incomeMapper::toDto);
	}

	@Override
	public Page<IncomeResponseDto> getFilteredIncomesBetweenDates(String email, String startDate, String endDate,
			int page, int size) {
		System.out.println(startDate + "   " + endDate);
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found with email: " + email);
		}

		UserEntity user = userOptional.get();
		int userId = user.getId();

		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);

		Pageable pageable = PageRequest.of(page, size, Sort.by("incomeDate").descending());

		Page<Income> filteredIncomes = incomeRepository.findByUserIdAndDateRangeAndIsDeletedFalse(userId, start, end,
				pageable);

		return filteredIncomes.map(incomeMapper::toDto);
	}
}
