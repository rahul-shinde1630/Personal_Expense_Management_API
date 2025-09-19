package com.pem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.pem.entity.Budget;

@Component
public interface BudgetRepository extends JpaRepository<Budget, Long> {

	List<Budget> findByUserEmailAndIsDeletedFalse(String email);

	@Query("SELECT DISTINCT b.category FROM Budget b WHERE b.user.email = :email AND b.isDeleted = false")
	List<String> findDistinctCategoriesByUser(@Param("email") String email);

	Page<Budget> findByUserEmail(String email, Pageable pageable);

	Page<Budget> findByUserEmailAndCategory(String email, String category, Pageable pageable);

}
