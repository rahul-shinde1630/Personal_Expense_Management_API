package com.pem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pem.entity.BorrowedMoney;

public interface BorrowedMoneyRepository extends JpaRepository<BorrowedMoney, Integer> {

	BorrowedMoney findById(Long id);

	List<BorrowedMoney> findByUserEmailAndIsDeletedFalse(String email);

}
