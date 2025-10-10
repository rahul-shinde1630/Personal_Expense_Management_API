package com.pem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "banks")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bank_id;

	@NotBlank(message = "Bank name is required")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User is required")
	private UserEntity user;

	public Bank() {
	}

	public Bank(int bank_id, String name, UserEntity user) {
		this.bank_id = bank_id;
		this.name = name;
		this.user = user;
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
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
}
