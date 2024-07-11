package project.rate.currency.service;

import java.net.URISyntaxException;

import project.rate.currency.dto.RateDtoUSD;

public interface RateCurrencyService {
	
	boolean  exchangeRates() throws URISyntaxException;
	
	

}
