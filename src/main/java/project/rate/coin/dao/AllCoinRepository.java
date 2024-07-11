package project.rate.coin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.rate.coin.model.AllCoin;

public interface AllCoinRepository  extends JpaRepository<AllCoin, String>{

}
