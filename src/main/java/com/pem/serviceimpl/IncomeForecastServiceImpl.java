package com.pem.serviceimpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.income.IncomeForecastResponseDto;
import com.pem.repository.IncomeRepository;
import com.pem.service.IncomeForecastService;

@Service
public class IncomeForecastServiceImpl implements IncomeForecastService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public IncomeForecastResponseDto forecastIncome(String email, int months) {
		LocalDate startDate = LocalDate.now().minusMonths(months);
		List<Object[]> data = incomeRepository.findIncomeByCategory(email, startDate);

		Map<String, Double> forecastMap = new HashMap<>();
		double total = 0.0;

		for (Object[] row : data) {
			String category = (String) row[0];
			Double amount = (Double) row[1];

			// Simple monthly moving average
			Double forecasted = amount / months;
			forecastMap.put(category, forecasted);
			total += forecasted;
		}

		IncomeForecastResponseDto dto = new IncomeForecastResponseDto();
		dto.setForecastByCategory(forecastMap);
		dto.setTotalExpectedIncome(total);
		return dto;
	}
}
