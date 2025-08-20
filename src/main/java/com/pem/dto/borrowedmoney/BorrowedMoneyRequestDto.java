package com.pem.dto.borrowedmoney;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedMoneyRequestDto {
	private int id;

	private String user;
	private String personName;
	private double amount;
	private LocalDate borrowedDate;
	private LocalDate dueDate;
	private String reason;
	private double remainingAmount;
	private String status;
}
