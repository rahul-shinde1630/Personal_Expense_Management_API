package com.pem.dto.repayment;

import java.time.LocalDate;

import com.pem.enums.TransactionType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RepaymentRequestDto {

	private Long borrowedId;
	private Long lentId;
	private String user;
	private String personName;
	private double amount;
	private TransactionType transactionType;
	private LocalDate repaymentsDate;
	private double remainingAmount;
	private String status;

}
