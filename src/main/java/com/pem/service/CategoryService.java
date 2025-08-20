package com.pem.service;

import java.util.List;

import com.pem.dto.category.CategoryDto;
import com.pem.dto.category.CategoryRequestDto;

public interface CategoryService {
	boolean saveCategory(CategoryRequestDto dto);

	List<CategoryDto> getAllCategoriesByUserAndType(String user, String type);

	List<CategoryDto> getCategoriesByUser(String email);
}
