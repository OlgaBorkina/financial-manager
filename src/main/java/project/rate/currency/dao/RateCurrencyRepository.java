package project.rate.currency.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.rate.currency.model.Rate;



public interface RateCurrencyRepository extends JpaRepository<Rate,LocalDateTime>{
	@Query(nativeQuery = true, value = "SELECT TOP 1 TARIFF  FROM DATE_VALUTA_TARIFF WHERE (RATE > ?1 AND CURRENCY_CODE  = ?2)")
	Double checkCurrencyCode(LocalDateTime localDateTime, String currencyCode);
	
	@Query(nativeQuery = true, value = "SELECT TOP 1 TARIFF  FROM DATE_VALUTA_TARIFF WHERE (RATE > ?1 AND CURRENCY_CODE  = ?2)")
	Double  searchExchangeRate (LocalDateTime localDateTime, String currencyCode);
	
//	@Query(nativeQuery = true, value = "SELECT TOP 1 RATE  FROM ?2 WHERE RATES_DATE_TIME  > ?1")
//	Double  searchRateCoin (LocalDateTime localDateTime, String currencyCode);
//	
//	@Query(nativeQuery = true, value = "?")
//	Double  searchRateCoin (String str);
//	
	
//	@Query(nativeQuery = true, value = "SELECT TOP 1 TARIFF  FROM DATE_VALUTA_TARIFF WHERE (RATE > '2024-08-05T00:00:00' AND CURRENCY_CODE  = 'usd')")
//   SELECT TOP 1 RATE  FROM BNB_RATES  WHERE RATES_DATE_TIME  > '2024-08-05T00:00:00'	
//	List<Rate> findByRates(Map<String,Double> rates);

	
}
