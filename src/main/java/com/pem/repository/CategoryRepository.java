package com.pem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.pem.entity.Category;
import com.pem.enums.CategoryType;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByUser_EmailAndType(String user, CategoryType type);

	Category findByNameAndUser_Email(String name, String user);

	List<Category> findByUser_Email(String email);

	Optional<Category> findByName(String category);
}
