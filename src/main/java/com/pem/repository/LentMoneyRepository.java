package com.pem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pem.entity.LentMoney;

public interface LentMoneyRepository extends JpaRepository<LentMoney, Long> {
	List<LentMoney> findByUserEmailAndIsDeletedFalse(String email);

	Optional<LentMoney> findByLentId(Long id);

	List<LentMoney> findByUserEmail(String email);
}
