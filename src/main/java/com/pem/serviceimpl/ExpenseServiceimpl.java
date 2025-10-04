package com.pem.serviceimpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pem.dto.expense.ExpenseAnalysisResponseDto;
import com.pem.dto.expense.ExpenseDto;
import com.pem.dto.expense.ExpenseRequestDto;
import com.pem.dto.expense.ExpenseResponseDto;
import com.pem.dto.expense.UpdateExpenseDto;
import com.pem.entity.Bank;
import com.pem.entity.Category;
import com.pem.entity.Expense;
import com.pem.entity.UserEntity;
import com.pem.mapper.EditMapper;
import com.pem.mapper.ExpenseMapper;
import com.pem.repository.BankRepository;
import com.pem.repository.CategoryRepository;
import com.pem.repository.ExpenseRepository;
import com.pem.repository.UserRepository;
import com.pem.repository.UsersRepository;
import com.pem.service.ExpenseService;

@Service
public class ExpenseServiceimpl implements ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ExpenseMapper expenseMapper;
	@Autowired
	private EditMapper editMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean saveExpense(ExpenseRequestDto dto) {
		try {
			Expense entity = expenseMapper.toEntity(dto);
			expenseRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ExpenseDto getExpenseById(int id, String email) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found with email: " + email);
		}

		Expense expense = expenseRepository.findByIdAndUser(id, user);
		if (expense == null) {
			throw new RuntimeException("Expense not found for ID: " + id + " and user: " + email);
		}

		return editMapper.toDTO(expense);
	}

	@Override
	public boolean updateExpense(UpdateExpenseDto dto) {
		int expenseId = Integer.parseInt(dto.getId());

		Optional<Expense> optional = expenseRepository.findById(expenseId);
		Optional<UserEntity> userOptional = usersRepository.findByEmail(dto.getUser());

		Optional<Bank> bankOptional = bankRepository.findByName(dto.getAccount());
		Optional<Category> categoryOptional = categoryRepository.findByName(dto.getCategory());

		if (optional.isPresent() && userOptional.isPresent()) {
			Expense expense = optional.get();
			UserEntity user = userOptional.get();
			Bank bank = bankOptional.get();
			Category category = categoryOptional.get();
			expense.setAccount(bank);
			expense.setCategory(category);
			expense.setAmount(dto.getAmount());
			expense.setPaymentMode(dto.getPaymentMode());
			expense.setExpenseDate(dto.getExpenseDate());
			expense.setExpenseTime(dto.getExpenseTime());
			expense.setUser(user);

			expenseRepository.save(expense);
			return true;
		}

		return false;
	}

	@Override
	public List<ExpenseResponseDto> getExpensesBetweenDates(String email, String startDate, String endDate) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found with email: " + email);
		}

		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);

		List<Expense> expenses = expenseRepository.findByUserAndExpenseDateBetweenAndIsDeletedFalse(user, start, end);

		return expenses.stream().map(expenseMapper::toResponseDto).collect(Collectors.toList());
	}

	@Override
	public boolean deleteExpense(int id) {
		Optional<Expense> optional = expenseRepository.findById(id);
		if (optional.isPresent()) {
			Expense entity = optional.get();
			entity.setDeleted(true);
			expenseRepository.save(entity);
			return true;

		} else {
			System.out.println("LentMoney not found with id: " + id);
			return false;
		}
	}

	// ExpenseServiceImpl.java
	@Override
	public Page<ExpenseResponseDto> getFilteredExpensesWithPagination(String email, String filterType,
			String filterValue, int page, int size) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found with email: " + email);
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("expenseDate").descending());
		Page<Expense> filteredExpenses;

		switch (filterType != null ? filterType.toLowerCase() : "") {
		case "month":
			int month = Integer.parseInt(filterValue);
			filteredExpenses = expenseRepository.findByUserIdAndMonthAndIsDeletedFalse(user.getId(), month, pageable);
			break;

		case "year":
			int year = Integer.parseInt(filterValue);
			filteredExpenses = expenseRepository.findByUserIdAndYearAndIsDeletedFalse(user.getId(), year, pageable);
			break;

		case "custom":
			String[] dates = filterValue.split("_");
			LocalDate from = LocalDate.parse(dates[0]);
			LocalDate to = LocalDate.parse(dates[1]);
			filteredExpenses = expenseRepository.findByUserIdAndDateRangeAndIsDeletedFalse(user.getId(), from, to,
					pageable);
			break;

		default:
			filteredExpenses = expenseRepository.findByUserIdAndIsDeletedFalse(user.getId(), pageable);
			break;
		}

		return filteredExpenses.map(expenseMapper::toResponseDto);
	}

	@Override
	public ExpenseAnalysisResponseDto analyzeSpending(String email, int month, int year) {
		// Current month start and end
		LocalDate start = LocalDate.of(year, month, 1);
		LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

		// Total spent in current month
		Double totalSpent = expenseRepository.findTotalSpentBetween(email, start, end);
		if (totalSpent == null) {
			totalSpent = 0.0;
		}

		// Category-wise total
		List<Object[]> categoryData = expenseRepository.findCategoryWiseTotalBetween(email, start, end);

		Map<String, Double> categoryWisePercent = new HashMap<>();
		String topCategory = "";
		double topPercent = 0.0;

		// Calculate percentages
		if (totalSpent > 0) {
			for (Object[] row : categoryData) {
				String category = (String) row[0];
				Double amount = ((Number) row[1]).doubleValue();
				double percent = (amount / totalSpent) * 100;

				categoryWisePercent.put(category, percent);

				if (percent > topPercent) {
					topPercent = percent;
					topCategory = category;
				}
			}
		}

		// Last month comparison
		int lastMonth = (month == 1) ? 12 : month - 1;
		int lastYear = (month == 1) ? year - 1 : year;

		LocalDate lastStart = LocalDate.of(lastYear, lastMonth, 1);
		LocalDate lastEnd = lastStart.withDayOfMonth(lastStart.lengthOfMonth());

		Double lastMonthTotal = expenseRepository.findTotalSpentBetween(email, lastStart, lastEnd);
		if (lastMonthTotal == null) {
			lastMonthTotal = 0.0;
		}

		String monthComparison = "No data available for comparison";
		if (lastMonthTotal > 0 && totalSpent > 0) {
			double growth = ((totalSpent - lastMonthTotal) / lastMonthTotal) * 100;
			monthComparison = (growth >= 0)
					? "This month you spent " + String.format("%.2f", growth) + "% more compared to last month"
					: "This month you spent " + String.format("%.2f", Math.abs(growth))
							+ "% less compared to last month";
		}

		// Build response
		ExpenseAnalysisResponseDto dto = new ExpenseAnalysisResponseDto();
		dto.setTotalSpent(totalSpent);
		dto.setTopCategory(topCategory);
		dto.setTopCategoryPercent(topPercent);
		dto.setMonthComparison(monthComparison);
		dto.setCategoryWisePercent(categoryWisePercent);

		return dto;
	}

}
