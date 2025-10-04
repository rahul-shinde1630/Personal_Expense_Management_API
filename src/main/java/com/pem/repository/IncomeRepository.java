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

import com.pem.entity.Income;
import com.pem.entity.UserEntity;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	Optional<Income> findById(int incomeId);

	// Get all incomes for a user by user ID
	@Query("SELECT i FROM Income i WHERE i.user.id = :userId AND i.isDeleted = false")
	Page<Income> findByUserIdAndIsDeletedFalse(@Param("userId") int userId, Pageable pageable);

	// Filter incomes by exact date
	@Query("SELECT i FROM Income i WHERE i.user.id = :userId AND i.incomeDate = :date AND i.isDeleted = false")
	Page<Income> findByUserIdAndDateAndIsDeletedFalse(@Param("userId") int userId, @Param("date") LocalDate date,
			Pageable pageable);

	// Filter incomes by month
	@Query("SELECT i FROM Income i WHERE i.user.id = :userId AND MONTH(i.incomeDate) = :month AND YEAR(i.incomeDate) = :year AND i.isDeleted = false")
	Page<Income> findByUserIdAndMonthAndIsDeletedFalse(@Param("userId") int userId, @Param("month") int month,
			@Param("year") int year, Pageable pageable);

	// Filter incomes by year
	@Query("SELECT i FROM Income i WHERE i.user.id = :userId AND YEAR(i.incomeDate) = :year AND i.isDeleted = false")
	Page<Income> findByUserIdAndYearAndIsDeletedFalse(@Param("userId") int userId, @Param("year") int year,
			Pageable pageable);

	// Filter incomes by date range
	@Query("SELECT i FROM Income i WHERE i.user.id = :userId AND i.incomeDate BETWEEN :start AND :end AND i.isDeleted = false")
	Page<Income> findByUserIdAndDateRangeAndIsDeletedFalse(@Param("userId") int userId, @Param("start") LocalDate start,
			@Param("end") LocalDate end, Pageable pageable);

	// For export/download/date-range without pagination
	List<Income> findByUserAndIncomeDateBetweenAndIsDeletedFalse(UserEntity user, LocalDate start, LocalDate end);

	// For simple entity usage
	Page<Income> findByUserAndIsDeletedFalse(UserEntity user, Pageable pageable);

	@Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i "
			+ "WHERE i.user.id = :userId AND i.incomeDate BETWEEN :start AND :end")
	Double findByUserAndMonthYearAndIsDeletedFalse(@Param("userId") int userId, @Param("start") LocalDate start,
			@Param("end") LocalDate end);

	@Query("SELECT i.category.name, SUM(i.amount) " + "FROM Income i "
			+ "WHERE i.user.email = :email AND i.incomeDate >= :startDate " + "GROUP BY i.category.name")
	List<Object[]> findIncomeByCategory(@Param("email") String email, @Param("startDate") LocalDate startDate);

}
