package com.pem.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.borrowedmoney.BorrowedMoneyRequestDto;
import com.pem.dto.borrowedmoney.BorrowedMoneyResponseDto;
import com.pem.entity.BorrowedMoney;
import com.pem.entity.UserEntity;
import com.pem.mapper.BorrowedMoneyMapper;
import com.pem.repository.BorrowedMoneyRepository;
import com.pem.repository.BorrowedMoneyRepository2;
import com.pem.repository.UsersRepository;
import com.pem.service.BorrowedMoneyService;

@Service
public class BorrowedMoneyServiceImpl implements BorrowedMoneyService {

	@Autowired
	private BorrowedMoneyMapper borrowedMoneyMapper;
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BorrowedMoneyRepository repository;
	@Autowired
	private BorrowedMoneyRepository2 repository2;

	@Override
	public boolean addBorrow(BorrowedMoneyRequestDto dto) {
		Optional<UserEntity> userOpt = usersRepository.findByEmail(dto.getUser());
		if (userOpt.isPresent()) {
			BorrowedMoney b = new BorrowedMoney();
			b.setUser(userOpt.get());
			b.setPersonName(dto.getPersonName());
			b.setAmount(dto.getAmount());
			b.setBorrowedDate(dto.getBorrowedDate());
			b.setDueDate(dto.getDueDate());
			b.setReason(dto.getReason());
			b.setRemainingAmount(dto.getRemainingAmount());
			b.setStatus(BorrowedMoney.Status.valueOf(dto.getStatus()));
			repository.save(b);
			return true;
		}
		return false;
	}

	@Override
	public List<BorrowedMoneyResponseDto> getAllBorrows(String email) {
		List<BorrowedMoney> list = repository.findByUserEmailAndIsDeletedFalse(email);
		List<BorrowedMoneyResponseDto> dtoList = new ArrayList<>();

		for (BorrowedMoney b : list) {
			BorrowedMoneyResponseDto dto = borrowedMoneyMapper.toDto(b);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public BorrowedMoneyResponseDto getById(Long id) {
		BorrowedMoney borrowedMoney = repository.findById(id);
		BorrowedMoneyResponseDto dto = borrowedMoneyMapper.toResponseDto(borrowedMoney);
		return dto;
	}

	@Override
	public boolean update(BorrowedMoneyRequestDto dto) {
		try {
			BorrowedMoney existing = repository.findById(dto.getId())
					.orElseThrow(() -> new RuntimeException("Borrow record not found with id: " + dto.getId()));

			existing.setPersonName(dto.getPersonName());
			existing.setAmount(dto.getAmount());
			existing.setBorrowedDate(dto.getBorrowedDate());
			existing.setDueDate(dto.getDueDate());
			existing.setReason(dto.getReason());
			existing.setRemainingAmount(dto.getRemainingAmount());
			existing.setStatus(BorrowedMoney.Status.valueOf(dto.getStatus()));
			repository.save(existing);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		Optional<BorrowedMoney> opt = repository2.findById(id);
		if (opt.isPresent()) {
			BorrowedMoney entity = opt.get();
			entity.setDeleted(true);
			repository.save(entity);
			return true;
		} else {
			System.out.println("LentMoney not found with id: " + id);
			return false;
		}

	}

	@Override
	public List<BorrowedMoneyResponseDto> getUnpaidByEmail(String email) {
		if (email == null || email.isEmpty()) {
			return Collections.emptyList();
		}

		// Use the entity's nested Status enum
		BorrowedMoney.Status paidStatus = BorrowedMoney.Status.PAID;

		// Call repository with @Query
		List<BorrowedMoney> borrowedList = repository.findUnpaidByEmail(email, paidStatus);

		// Convert to DTOs
		return borrowedList.stream().map(borrowedMoneyMapper::toResponseDto).collect(Collectors.toList());
	}

}
