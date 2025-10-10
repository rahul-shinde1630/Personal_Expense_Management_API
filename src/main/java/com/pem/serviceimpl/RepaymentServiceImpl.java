package com.pem.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.repayment.RepaymentRequestDto;
import com.pem.dto.repayment.RepaymentResponseDto;
import com.pem.entity.BorrowedMoney;
import com.pem.entity.LentMoney;
import com.pem.entity.Repayment;
import com.pem.entity.UserEntity;
import com.pem.enums.TransactionType;
import com.pem.mapper.RepaymentMapper;
import com.pem.repository.BorrowedMoneyRepository2;
import com.pem.repository.LentMoneyRepository;
import com.pem.repository.RepaymentRepository;
import com.pem.repository.UserRepository;
import com.pem.service.RepaymentService;

@Service
public class RepaymentServiceImpl implements RepaymentService {

	@Autowired
	private RepaymentRepository repaymentRepository;
	@Autowired
	private BorrowedMoneyRepository2 borrowedMoneyRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LentMoneyRepository lentMoneyRepository;

	@Autowired
	private RepaymentMapper repaymentMapper;

	@Override
	public RepaymentResponseDto addRepayment(RepaymentRequestDto requestDto) {

		Repayment repayment = repaymentMapper.toEntity(requestDto);
		Repayment repayments = repaymentRepository.save(repayment);

		if (requestDto.getTransactionType() == TransactionType.LENT) {
			lentMoneyRepository.findByLentId(requestDto.getLentId()).ifPresent(lent -> {
				double newRemaining = lent.getRemainingAmount() - repayment.getAmount();
				lent.setRemainingAmount(newRemaining);

				if (newRemaining <= 0) {
					lent.setStatus(LentMoney.Status.PAID);
					lent.setRemainingAmount(0);
				} else {
					lent.setStatus(LentMoney.Status.PARTIAL);
				}
				lentMoneyRepository.save(lent);
			});
		} else if (requestDto.getTransactionType() == TransactionType.BORROWED) {
			borrowedMoneyRepository.findById(requestDto.getBorrowedId()).ifPresent(borrowed -> {
				double newRemaining = borrowed.getRemainingAmount() - repayment.getAmount();
				borrowed.setRemainingAmount(newRemaining);

				if (newRemaining <= 0) {
					borrowed.setStatus(BorrowedMoney.Status.PAID);
					borrowed.setRemainingAmount(0);
				} else {
					borrowed.setStatus(BorrowedMoney.Status.PARTIAL);
				}
				borrowedMoneyRepository.save(borrowed);
			});
		}

		return repaymentMapper.toDto(repayments);
	}

	@Override
	public List<RepaymentResponseDto> getFilteredRepayments(String email, String repaymentType, String filterValue) {

		List<Repayment> repayments;

		if (filterValue != null && !filterValue.isEmpty()) {
			repayments = repaymentRepository.findByUserEmailAndTransactionTypeAndStatus(email, repaymentType,
					filterValue);
		} else {
			repayments = repaymentRepository.findByUserEmailAndTransactionType(email, repaymentType);
		}

		return repayments.stream().map(repaymentMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public RepaymentResponseDto getRepaymentById(Long id) {
		return repaymentRepository.findByRepaymentId(id).map(repaymentMapper::toDto).orElse(null);
	}

	@Override
	public void updateRepayment(RepaymentRequestDto dto) {
		repaymentRepository.findByRepaymentId(dto.getLentId()).ifPresent(existing -> {
			Repayment updated = repaymentMapper.toEntity(dto); // convert DTO to entity
			updated.setRepaymentId(existing.getRepaymentId()); // keep existing ID
			repaymentRepository.save(updated);
		});
	}

	@Override
	public boolean deleteById(Long id) {
		Optional<Repayment> optional = repaymentRepository.findByRepaymentId(id);
		if (optional.isPresent()) {
			Repayment repayment = optional.get();
			repayment.setDeleted(true);
			repaymentRepository.save(repayment);
			return true;
		} else {
			System.out.println("Income not found with id: " + id);
			return false;
		}
	}

	@Override
	public List<RepaymentResponseDto> getRepaymentsByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("User not found with email: " + email);
		}

		List<Repayment> repayments = repaymentRepository.findByUser(user);
		List<RepaymentResponseDto> dtoList = new ArrayList<>();

		for (Repayment repayment : repayments) {
			dtoList.add(repaymentMapper.toDto(repayment));
		}

		return dtoList;
	}

}
