package com.pem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.category.CategoryDto;
import com.pem.dto.category.CategoryRequestDto;
import com.pem.service.CategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDto dto) {
		boolean added = categoryService.saveCategory(dto);
		return ResponseEntity.ok(added);
	}

	@GetMapping("/categories")
	public List<CategoryDto> getCategoriesByType(@RequestParam String user, @RequestParam String type) {
		return categoryService.getAllCategoriesByUserAndType(user, type);
	}

	@GetMapping("/categoriess")
	public ResponseEntity<List<CategoryDto>> getCategoriesByUser(@RequestParam String email) {
		List<CategoryDto> categories = categoryService.getCategoriesByUser(email);
		return ResponseEntity.ok(categories);
	}

}
