package com.pem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pem.dto.repayment.RepaymentRequestDto;
import com.pem.dto.repayment.RepaymentResponseDto;
import com.pem.entity.Repayment;
import com.pem.entity.UserEntity;
import com.pem.repository.UserRepository;

@Component
public class RepaymentMapper {

	@Autowired
	private UserRepository userRepository;

	public Repayment toEntity(RepaymentRequestDto dto) {
		Repayment repayment = new Repayment();

		// Reference ID handling
		if (dto.getReferenceId() != null) {
			repayment.setReferenceId(dto.getReferenceId());
		} else if (dto.getLentId() != null) {
			repayment.setReferenceId(dto.getLentId());
		} else if (dto.getBorrowedId() != null) {
			repayment.setReferenceId(dto.getBorrowedId());
		} else {
			throw new IllegalArgumentException("Reference ID cannot be null");
		}

		repayment.setPersonName(dto.getPersonName());
		repayment.setTransactionType(dto.getTransactionType());
		repayment.setAmount(dto.getAmount());
		repayment.setRepaymentDate(dto.getRepaymentsDate());
		repayment.setStatus(dto.getStatus());
		repayment.setRemainingAmount(dto.getRemainingAmount());

		if (dto.getUser() != null) {
			UserEntity user = userRepository.findByEmail(dto.getUser());
			repayment.setUser(user);
		}

		return repayment;
	}

	public RepaymentResponseDto toDto(Repayment repayment) {
		RepaymentResponseDto dto = new RepaymentResponseDto();
		dto.setRepaymentId(repayment.getRepaymentId());
		dto.setReferenceId(repayment.getReferenceId());
		dto.setTransactionType(repayment.getTransactionType().name());
		dto.setAmount(repayment.getAmount());
		dto.setRepaymentDate(repayment.getRepaymentDate());
		dto.setStatus(repayment.getStatus());
		dto.setRemainingAmount(repayment.getRemainingAmount());
		dto.setPersonName(repayment.getPersonName());
		return dto;
	}
}
