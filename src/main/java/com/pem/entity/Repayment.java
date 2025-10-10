package com.pem.entity;

import java.time.LocalDate;

import com.pem.enums.TransactionType;

import jakarta.persistence.Column;
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
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "repayments")
public class Repayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long repaymentId;

	@NotBlank(message = "Person name is required")
	private String personName;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private UserEntity user;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Transaction type is required")
	private TransactionType transactionType;

	@Column(name = "reference_id")
	@Positive(message = "Reference ID must be positive")
	private Long referenceId;

	@Positive(message = "Amount must be positive")
	private double amount;

	@PositiveOrZero(message = "Remaining amount must be zero or positive")
	private double remainingAmount;

	@PastOrPresent(message = "Repayment date cannot be in the future")
	private LocalDate repaymentDate;

	@NotBlank(message = "Status is required")
	private String status;

	@Column(name = "is_deleted")
	private boolean deleted = false;

	public Repayment() {
	}

	public Repayment(Long repaymentId, String personName, UserEntity user, TransactionType transactionType,
			Long referenceId, double amount, double remainingAmount, LocalDate repaymentDate, String status,
			boolean deleted) {
		super();
		this.repaymentId = repaymentId;
		this.personName = personName;
		this.user = user;
		this.transactionType = transactionType;
		this.referenceId = referenceId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.repaymentDate = repaymentDate;
		this.status = status;
		this.deleted = deleted;
	}

	// Getters & Setters
	public Long getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(Long repaymentId) {
		this.repaymentId = repaymentId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public LocalDate getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(LocalDate repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

}
