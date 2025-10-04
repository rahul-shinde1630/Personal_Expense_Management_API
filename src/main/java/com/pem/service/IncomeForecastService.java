package com.pem.service;

import com.pem.dto.income.IncomeForecastResponseDto;

public interface IncomeForecastService {

	IncomeForecastResponseDto forecastIncome(String email, int months);
}
