package com.pem.dto.repayment;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepaymentResponseDto {
	private Long repaymentId;
	private String transactionType;
	private String personName;
	private long referenceId;
	private double amount;
	private LocalDate repaymentDate;
	private String status;
	private double remainingAmount;

}
