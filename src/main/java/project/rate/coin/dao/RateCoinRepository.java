package project.rate.coin.dao;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import project.rate.coin.model.CoinBtc;
import project.rate.coin.model.CoinStandard;



public interface RateCoinRepository extends JpaRepository <CoinStandard,LocalDateTime>{

	

}
