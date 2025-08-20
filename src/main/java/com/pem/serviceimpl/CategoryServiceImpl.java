package com.pem.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.category.CategoryDto;
import com.pem.dto.category.CategoryRequestDto;
import com.pem.entity.Category;
import com.pem.entity.UserEntity;
import com.pem.enums.CategoryType;
import com.pem.mapper.CategoryMapper;
import com.pem.repository.CategoryRepository;
import com.pem.repository.UserRepository;
import com.pem.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public boolean saveCategory(CategoryRequestDto dto) {
		try {
			Category category = new Category();
			category.setName(dto.getName());
			if (dto.getUser() != null) {
				UserEntity user = userRepository.findByEmail(dto.getUser());
				category.setUser(user);
			}
			category.setType(dto.getType());

			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			System.out.println("Error saving category: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<CategoryDto> getAllCategoriesByUserAndType(String user, String type) {
		return categoryRepository.findByUser_EmailAndType(user, CategoryType.valueOf(type.toUpperCase())).stream()
				.map(cat -> new CategoryDto(cat.getCategory_id(), cat.getName(), cat.getType().toString()))
				.collect(Collectors.toList());
	}

	@Override
	public List<CategoryDto> getCategoriesByUser(String email) {
		List<Category> categoryList = categoryRepository.findByUser_Email(email);
		return categoryList.stream().map(categoryMapper::toDto).collect(Collectors.toList());
	}
}
