package com.pem.service;

import java.util.List;

import com.pem.dto.repayment.RepaymentRequestDto;
import com.pem.dto.repayment.RepaymentResponseDto;

public interface RepaymentService {

	RepaymentResponseDto addRepayment(RepaymentRequestDto dto);

	RepaymentResponseDto getRepaymentById(Long id);

	void updateRepayment(RepaymentRequestDto dto);

	boolean deleteById(Long id);

	List<RepaymentResponseDto> getFilteredRepayments(String email, String repaymentType, String filterValue);

	List<RepaymentResponseDto> getRepaymentsByEmail(String email);
}
