package project.rate.currency.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.rate.currency.dto.RateDtoUSD;
import project.rate.currency.service.RateCurrencyService;

@RestController
@RequiredArgsConstructor
public class RateCurrencyController {

	final RateCurrencyService rateService;
	
	@GetMapping("/rate")
	public boolean exchangeRates () throws URISyntaxException {
		return rateService.exchangeRates();
	}
	
}
