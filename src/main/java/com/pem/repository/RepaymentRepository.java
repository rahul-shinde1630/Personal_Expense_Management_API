package com.pem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pem.entity.Repayment;
import com.pem.entity.UserEntity;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Integer> {
	List<Repayment> findByUser(UserEntity user);

	Optional<Repayment> findByRepaymentId(Long id);

	List<Repayment> findByUserEmailAndTransactionTypeAndStatus(String email, String repaymentType, String filterValue);

	List<Repayment> findByUserEmailAndTransactionType(String email, String repaymentType);
}
