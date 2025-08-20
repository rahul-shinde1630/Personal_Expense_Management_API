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

@Entity
@Table(name = "borrowed_money")
public class BorrowedMoney {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	private String personName;
	private double amount;
	private LocalDate borrowedDate;
	private LocalDate dueDate;
	private String reason;
	private double remainingAmount;

	@Enumerated(EnumType.STRING)
	private Status status;

	public enum Status {
		Pending, Partially_Paid, Paid
	}

	private boolean isDeleted = false;

	public BorrowedMoney() {
		// TODO Auto-generated constructor stub
	}

	public BorrowedMoney(Long id, UserEntity user, String personName, double amount, LocalDate borrowedDate,
			LocalDate dueDate, String reason, double remainingAmount, Status status, boolean isDeleted) {
		super();
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

	// getters & setters

	public Long getId() {
		return id;
	}

	public void id(Long id) {
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

	public void setId(Long id) {
		this.id = id;
	}

}
