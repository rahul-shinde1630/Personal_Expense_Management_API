package com.pem.service;

import java.util.List;

import com.pem.dto.borrowedmoney.BorrowedMoneyRequestDto;
import com.pem.dto.borrowedmoney.BorrowedMoneyResponseDto;

public interface BorrowedMoneyService {
	boolean addBorrow(BorrowedMoneyRequestDto dto);

	List<BorrowedMoneyResponseDto> getAllBorrows(String email);

	BorrowedMoneyResponseDto getById(Long id);

	boolean update(BorrowedMoneyRequestDto dto);

	boolean delete(Long id);
}
