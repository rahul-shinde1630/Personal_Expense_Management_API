
package com.pem.dto.borrowedmoney;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedMoneyResponseDto {
	private Long id;
	private String personName;
	private double amount;
	private LocalDate borrowedDate;
	private LocalDate dueDate;
	private String reason;
	private double remainingAmount;
	private String status;
	private String user;
}
