package project.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.communication.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, String> {

}
