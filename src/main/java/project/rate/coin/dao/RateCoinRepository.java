package project.rate.coin.dao;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.rate.coin.model.CoinBtc;
import project.rate.coin.model.CoinStandard;



public interface RateCoinRepository extends JpaRepository <CoinStandard,LocalDateTime>{
//
//	@Query(nativeQuery = true, value = "SELECT TOP 1 RATE  FROM ?2 WHERE RATES_DATE_TIME  > ?1")
//	Double  searchRateCoin (LocalDateTime localDateTime, String currencyCode);
//	
//	@Query(nativeQuery = true, value = "?")
//	Double  searchRateCoin (String str);
//	
	

}
