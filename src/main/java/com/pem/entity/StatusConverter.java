package com.pem.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<LentMoney.Status, String> {

	@Override
	public String convertToDatabaseColumn(LentMoney.Status status) {
		return status == null ? null : status.name(); // saves as "PENDING", "PAID", "PARTIAL"
	}

	@Override
	public LentMoney.Status convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return LentMoney.Status.valueOf(dbData.toUpperCase()); // converts "Paid" -> "PAID"
	}
}
