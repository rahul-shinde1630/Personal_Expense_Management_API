package com.pem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pem.dto.income.IncomeForecastResponseDto;
import com.pem.service.IncomeForecastService;

@RestController
@RequestMapping("/api/income")
public class IncomeForecastRestController {

	@Autowired
	private IncomeForecastService incomeForecastService;

	@GetMapping("/forecast")
	public IncomeForecastResponseDto getIncomeForecast(@RequestParam("email") String email) {

		IncomeForecastResponseDto forecast = incomeForecastService.forecastIncome(email, 6);

		return forecast;
	}
}
