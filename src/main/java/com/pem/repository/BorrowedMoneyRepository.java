package com.pem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pem.entity.BorrowedMoney;

public interface BorrowedMoneyRepository extends JpaRepository<BorrowedMoney, Integer> {

	BorrowedMoney findById(Long id);

	List<BorrowedMoney> findByUserEmailAndIsDeletedFalse(String email);

	@Query("SELECT COALESCE(SUM(b.remainingAmount), 0) FROM BorrowedMoney b "
			+ "WHERE b.user.id = :userId AND MONTH(b.dueDate) = :month AND YEAR(b.dueDate) = :year AND b.isDeleted = false")
	Double findRemainingByUserAndMonthYear(@Param("userId") int userId, @Param("month") int month,
			@Param("year") int year);

	@Query("SELECT b FROM BorrowedMoney b WHERE b.user.email = :email AND b.isDeleted = false AND b.status <> :status")
	List<BorrowedMoney> findUnpaidByEmail(@Param("email") String email, @Param("status") BorrowedMoney.Status status);

}
