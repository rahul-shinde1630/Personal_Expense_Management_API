package com.pem.service;

public interface ReportService {

	Double getTotalIncomeBetweenYears(String email, int fromYear, int toYear);

	Double getTotalExpenseBetweenYears(String email, int fromYear, int toYear);

	Double getBorrowedRemainingBetweenYears(String email, int fromYear, int toYear);
}
