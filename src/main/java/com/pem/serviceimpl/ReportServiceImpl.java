package com.pem.serviceimpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.entity.UserEntity;
import com.pem.repository.IncomeRepository;
import com.pem.repository.UsersRepository;
import com.pem.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private UsersRepository userRepository;

	@Override
	public Double getIncomeByUserAndMonthAndYear(String email, int month, int year) {
		// 1. Get user by email
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);

		if (userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			int userId = user.getId();

			// 2. Get total income using userId
			LocalDate start = LocalDate.of(year, month, userId);
			LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

			return incomeRepository.findByUserAndMonthYearAndIsDeletedFalse(userId, start, end);
		} else {
			// If user not found
			throw new RuntimeException("User not found with email: " + email);
		}

	}
}
