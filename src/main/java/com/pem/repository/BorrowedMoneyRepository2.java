package com.pem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pem.entity.BorrowedMoney;

public interface BorrowedMoneyRepository2 extends JpaRepository<BorrowedMoney, Integer> {
	Optional<BorrowedMoney> findById(Long id);

}
