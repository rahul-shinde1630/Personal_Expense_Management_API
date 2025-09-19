package com.pem.serviceimpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.entity.UserEntity;
import com.pem.repository.BorrowedMoneyRepository;
import com.pem.repository.ExpenseRepository;
import com.pem.repository.IncomeRepository;
import com.pem.repository.UsersRepository;
import com.pem.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private BorrowedMoneyRepository borrowedMoneyRepository;
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Double getIncomeByUserAndMonthAndYear(String email, int month, int year) {
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);

		if (userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			int userId = user.getId();

			LocalDate start = LocalDate.of(year, month, 1);
			LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

			Double total = incomeRepository.findByUserAndMonthYearAndIsDeletedFalse(userId, start, end);
			return total != null ? total : 0.0;
		} else {
			throw new RuntimeException("User not found with email: " + email);
		}
	}

	@Override
	public Double getExpenseByUserAndMonthAndYear(String email, int month, int year) {
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);

		if (userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			int userId = user.getId();

			LocalDate start = LocalDate.of(year, month, 1);
			LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

			Double total = expenseRepository.findByTotalUserAndMonthYearAndIsDeletedFalse(userId, start, end);
			return total != null ? total : 0.0;
		} else {
			throw new RuntimeException("User not found with email: " + email);
		}
	}

	@Override
	public Double getBorrowedRemaining(String email, int month, int year) {
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			int userId = user.getId();

			Double remaining = borrowedMoneyRepository.findRemainingByUserAndMonthYear(userId, month, year);
			return remaining != null ? remaining : 0.0;
		} else {
			throw new RuntimeException("User not found with email: " + email);
		}
	}

}
