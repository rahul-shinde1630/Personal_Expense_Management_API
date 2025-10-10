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
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "lent_money")
public class LentMoney {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lentId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private UserEntity user;

	@NotBlank(message = "Person name is required")
	private String personName;

	@Min(value = 1, message = "Amount must be greater than 0")
	private double amount;

	@NotNull(message = "Lent date is required")
	@PastOrPresent(message = "Lent date cannot be in the future")
	private LocalDate lentDate;

	@NotNull(message = "Due date is required")
	private LocalDate dueDate;

	@NotBlank(message = "Reason is required")
	private String reason;

	@Min(value = 0, message = "Remaining amount cannot be negative")
	private double remainingAmount = 0.0;

	private boolean isDeleted = false;

	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	public enum Status {
		PENDING, PARTIAL, PAID
	}

	public LentMoney() {
	}

	public LentMoney(Long lentId, UserEntity user, String personName, double amount, LocalDate lentDate,
			LocalDate dueDate, String reason, double remainingAmount, boolean isDeleted, Status status) {
		this.lentId = lentId;
		this.user = user;
		this.personName = personName;
		this.amount = amount;
		this.lentDate = lentDate;
		this.dueDate = dueDate;
		this.reason = reason;
		this.remainingAmount = remainingAmount;
		this.isDeleted = isDeleted;
		this.status = status;
	}

	// Getters and Setters
	public Long getLentId() {
		return lentId;
	}

	public void setLentId(Long lentId) {
		this.lentId = lentId;
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

	public LocalDate getLentDate() {
		return lentDate;
	}

	public void setLentDate(LocalDate lentDate) {
		this.lentDate = lentDate;
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
