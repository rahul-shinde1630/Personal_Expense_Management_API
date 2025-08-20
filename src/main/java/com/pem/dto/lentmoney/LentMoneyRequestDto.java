package com.pem.dto.lentmoney;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LentMoneyRequestDto {
	private Long lentId;
	private String user;
	private String personName;
	private double amount;
	private LocalDate lentDate;
	private LocalDate dueDate;
	private String reason;
	private double remainingAmount;
	private String status;
}
