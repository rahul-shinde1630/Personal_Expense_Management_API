package com.pem.mapper;

import org.springframework.stereotype.Component;

import com.pem.dto.category.CategoryDto;
import com.pem.entity.Category;

@Component
public class CategoryMapper {

	public CategoryDto toDto(Category category) {
		CategoryDto dto = new CategoryDto();
		dto.setId(category.getCategory_id());
		dto.setName(category.getName());
		return dto;
	}

	public Category toEntity(CategoryDto dto) {
		Category category = new Category();
		category.setCategory_id(dto.getId());
		category.setName(dto.getName());
		return category;
	}
}
