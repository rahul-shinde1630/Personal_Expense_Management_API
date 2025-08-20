package com.pem.dto.category;

import com.pem.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {
	private Long id;
	private String name;
	private String user;
	private CategoryType type;
}
