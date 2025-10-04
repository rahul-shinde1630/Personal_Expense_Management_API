package com.pem.dto.income;

import java.util.Map;

public class IncomeForecastResponseDto {

	private Map<String, Double> forecastByCategory;
	private Double totalExpectedIncome;

	public Map<String, Double> getForecastByCategory() {
		return forecastByCategory;
	}

	public void setForecastByCategory(Map<String, Double> forecastByCategory) {
		this.forecastByCategory = forecastByCategory;
	}

	public Double getTotalExpectedIncome() {
		return totalExpectedIncome;
	}

	public void setTotalExpectedIncome(Double totalExpectedIncome) {
		this.totalExpectedIncome = totalExpectedIncome;
	}
}
