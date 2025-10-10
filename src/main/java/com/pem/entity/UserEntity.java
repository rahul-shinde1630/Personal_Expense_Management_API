package com.pem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@Email(message = "Email is invalid")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	@Column(nullable = false)
	private String password;

	private String country;

	private boolean checkbox;

	public UserEntity(int user_id, String name, String email, String password, String country, boolean checkbox) {
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.country = country;
		this.checkbox = checkbox;
	}

	public UserEntity() {
	}

	public int getId() {
		return user_id;
	}

	public void setId(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
}
