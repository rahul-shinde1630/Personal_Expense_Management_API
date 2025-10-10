package com.pem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pem.entity.LentMoney;

public interface LentMoneyRepository extends JpaRepository<LentMoney, Long> {
	List<LentMoney> findByUserEmailAndIsDeletedFalse(String email);

	Optional<LentMoney> findByLentId(Long id);

	List<LentMoney> findByUserEmail(String email);

	@Query("SELECT l FROM LentMoney l WHERE l.user.email = :email AND l.isDeleted = false AND l.status <> :status")
	List<LentMoney> findUnpaidByEmail(@Param("email") String email, @Param("status") LentMoney.Status status);

}
