package com.pem.serviceimpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.repository.BorrowedMoneyRepository;
import com.pem.repository.ExpenseRepository;
import com.pem.repository.IncomeRepository;
import com.pem.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private BorrowedMoneyRepository borrowedMoneyRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Double getTotalIncomeBetweenYears(String email, int fromYear, int toYear) {
		LocalDate start = LocalDate.of(fromYear, 1, 1);
		LocalDate end = LocalDate.of(toYear, 12, 31);
		Double totalIncome = incomeRepository.findTotalIncomeBetween(email, start, end);
		return totalIncome != null ? totalIncome : 0.0;
	}

	@Override
	public Double getTotalExpenseBetweenYears(String email, int fromYear, int toYear) {
		LocalDate start = LocalDate.of(fromYear, 1, 1);
		LocalDate end = LocalDate.of(toYear, 12, 31);
		Double totalExpense = expenseRepository.findTotalSpentBetween(email, start, end);
		return totalExpense != null ? totalExpense : 0.0;
	}

	@Override
	public Double getBorrowedRemainingBetweenYears(String email, int fromYear, int toYear) {
		LocalDate start = LocalDate.of(fromYear, 1, 1);
		LocalDate end = LocalDate.of(toYear, 12, 31);
		Double borrowedRemaining = borrowedMoneyRepository.findTotalBorrowedRemainingBetween(email, start, end);
		return borrowedRemaining != null ? borrowedRemaining : 0.0;
	}
}
