package project.rate.currency.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import project.rate.currency.dao.RateCurrencyRepository;
import project.rate.currency.dto.RateDtoUSD;
import project.rate.currency.model.Rate;


@Service
@RequiredArgsConstructor
public class RateCurrencyServiceImpl implements RateCurrencyService {
		
	final RateCurrencyRepository rateRepository;
	final ModelMapper modelMapper;

	@Override
//	@Scheduled(fixedRate = 60000)
	public boolean exchangeRates() throws URISyntaxException {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
    	URI url = new URI("https://latest.currency-api.pages.dev/v1/currencies/usd.json");
//		URI url = new URI("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@2024-08-03/v1/currencies/usd.json");
		RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);
		ResponseEntity<RateDtoUSD> response = restTemplate.exchange(request, RateDtoUSD.class);
		
		RateDtoUSD rateDtoUsd = response.getBody();
		Map<String, Double> rates = rateDtoUsd.getUsd();
		
		Rate rate = new Rate(LocalDateTime.now(), rates);
		
		System.out.println(rateDtoUsd.getDate());
		rateRepository.save(rate);
		return true;
	}
	
	   public boolean checkCurrencyCode(String currencyCode) {
		   
		return false;
		   
	   }
	

	//   SELECT * FROM DATE_VALUTA_TARIFF WHERE (RATE > '2024-08-05T00:00:00' AND CURRENCY_CODE  = 'ksm') ;
	// SELECT TARIFF  FROM DATE_VALUTA_TARIFF WHERE (RATE > '2024-08-05T00:00:00' AND CURRENCY_CODE  = 'usd') ;

}
