package com.pem.entity;

import com.pem.enums.CategoryType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;

	@NotBlank(message = "Category name is required")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotNull(message = "User is required")
	private UserEntity user;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Category type is required")
	private CategoryType type;

	public Category() {
	}

	public Category(int category_id, String name, UserEntity user, CategoryType type) {
		this.category_id = category_id;
		this.name = name;
		this.user = user;
		this.type = type;
	}

	// Getters and Setters
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}
}
