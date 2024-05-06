package project.communication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.communication.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, String> {

}
