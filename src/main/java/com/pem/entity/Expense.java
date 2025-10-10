package com.pem.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "expenses")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Positive(message = "Amount must be greater than zero")
	private double amount;

	@NotNull(message = "Expense date is required")
	private LocalDate expenseDate;

	@NotBlank(message = "Expense time cannot be blank")
	@Length(max = 10, message = "Expense time should not exceed 10 characters (e.g. '10:30 AM')")
	private String expenseTime;

	@NotBlank(message = "Payment mode cannot be blank")
	private String paymentMode;

	private boolean isDeleted = false;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Category is required")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "bank_id", nullable = false)
	@NotNull(message = "Bank account is required")
	private Bank account;

	// Default constructor
	public Expense() {
	}

	// Parameterized constructor
	public Expense(int id, double amount, LocalDate expenseDate, String expenseTime, String paymentMode,
			boolean isDeleted, Category category, UserEntity user, Bank account) {
		this.id = id;
		this.amount = amount;
		this.expenseDate = expenseDate;
		this.expenseTime = expenseTime;
		this.paymentMode = paymentMode;
		this.isDeleted = isDeleted;
		this.category = category;
		this.user = user;
		this.account = account;
	}

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getExpenseTime() {
		return expenseTime;
	}

	public void setExpenseTime(String expenseTime) {
		this.expenseTime = expenseTime;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Bank getAccount() {
		return account;
	}

	public void setAccount(Bank account) {
		this.account = account;
	}
}
