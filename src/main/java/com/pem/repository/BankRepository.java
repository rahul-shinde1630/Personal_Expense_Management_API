package com.pem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pem.entity.Bank;
import com.pem.entity.UserEntity;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

	List<Bank> findByUser_Email(String email);

	List<Bank> findByUser(UserEntity user);

	Bank findByNameAndUser_Email(String name, String email); // ✅ Also valid

	Optional<Bank> findByName(String account);
}
