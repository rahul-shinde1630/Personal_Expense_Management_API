package com.pem.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "borrowed_money")
public class BorrowedMoney {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private UserEntity user;

	@NotBlank(message = "Person name is required")
	private String personName;

	@Min(value = 0, message = "Amount must be non-negative")
	private double amount;

	@NotNull(message = "Borrowed date is required")
	private LocalDate borrowedDate;

	@NotNull(message = "Due date is required")
	private LocalDate dueDate;

	private String reason;

	@Min(value = 0, message = "Remaining amount must be non-negative")
	private double remainingAmount;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is required")
	private Status status = Status.PENDING;

	public enum Status {
		PENDING, PARTIAL, PAID
	}

	private boolean isDeleted = false;

	public BorrowedMoney() {
	}

	public BorrowedMoney(Long id, UserEntity user, String personName, double amount, LocalDate borrowedDate,
			LocalDate dueDate, String reason, double remainingAmount, Status status, boolean isDeleted) {
		this.id = id;
		this.user = user;
		this.personName = personName;
		this.amount = amount;
		this.borrowedDate = borrowedDate;
		this.dueDate = dueDate;
		this.reason = reason;
		this.remainingAmount = remainingAmount;
		this.status = status;
		this.isDeleted = isDeleted;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(LocalDate borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
