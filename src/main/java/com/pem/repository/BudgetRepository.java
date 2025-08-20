package com.pem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.pem.entity.Budget;

@Component
public interface BudgetRepository extends JpaRepository<Budget, Long> {

	List<Budget> findByUserEmailAndIsDeletedFalse(String email);

}
