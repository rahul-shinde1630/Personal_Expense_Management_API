package com.pem.service;

public interface ReportService {
	Double getIncomeByUserAndMonthAndYear(String email, int month, int year);

	Double getExpenseByUserAndMonthAndYear(String email, int month, int year);

	Double getBorrowedRemaining(String email, int month, int year);
}
