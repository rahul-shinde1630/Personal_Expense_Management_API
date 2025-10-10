package com.pem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "budgets")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Title must contain only letters and spaces")
	private String title;

	@DecimalMin(value = "0.1", inclusive = true, message = "Amount must be greater than 0")
	private double amount;

	@NotBlank(message = "Category is required")
	private String category;

	@NotNull(message = "Start date is required")
	@Column(name = "from_date")
	private LocalDate fromDate;

	@NotNull(message = "End date is required")
	@Column(name = "to_date")
	private LocalDate toDate;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@NotNull(message = "User is required")
	private UserEntity user;

	private boolean isDeleted = false;

	public Budget() {
	}

	public Budget(Long id, String title, double amount, String category, LocalDate fromDate, LocalDate toDate,
			UserEntity user, boolean isDeleted) {
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.category = category;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.user = user;
		this.isDeleted = isDeleted;
	}

	// Getters and Setters
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
