package com.pem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pem.entity.Expense;
import com.pem.entity.UserEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	Optional<Expense> findById(Long id);

	@Query("SELECT e FROM Expense e WHERE e.user.id = :userId AND MONTH(e.expenseDate) = :month AND e.isDeleted = false")
	Page<Expense> findByUserIdAndMonthAndIsDeletedFalse(@Param("userId") int userId, @Param("month") int month,
			Pageable pageable);

	@Query("SELECT e FROM Expense e WHERE e.user.id = :userId AND YEAR(e.expenseDate) = :year AND e.isDeleted = false")
	Page<Expense> findByUserIdAndYearAndIsDeletedFalse(@Param("userId") int userId, @Param("year") int year,
			Pageable pageable);

	@Query("SELECT e FROM Expense e WHERE e.user.id = :userId AND e.expenseDate BETWEEN :from AND :to AND e.isDeleted = false")
	Page<Expense> findByUserIdAndDateRangeAndIsDeletedFalse(@Param("userId") int userId, @Param("from") LocalDate from,
			@Param("to") LocalDate to, Pageable pageable);

	@Query("SELECT e FROM Expense e WHERE e.user.id = :userId AND e.isDeleted = false")
	Page<Expense> findByUserIdAndIsDeletedFalse(@Param("userId") int userId, Pageable pageable);

	Expense findByIdAndUser(int id, UserEntity user);

	List<Expense> findByUserAndExpenseDateBetweenAndIsDeletedFalse(UserEntity user, LocalDate start, LocalDate end);

	@Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e "
			+ "WHERE e.user.id = :userId AND e.expenseDate BETWEEN :start AND :end AND e.isDeleted = false")
	Double findByTotalUserAndMonthYearAndIsDeletedFalse(@Param("userId") int userId, @Param("start") LocalDate start,
			@Param("end") LocalDate end);
}
