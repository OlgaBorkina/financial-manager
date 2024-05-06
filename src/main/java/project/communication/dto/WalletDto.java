package project.communication.dto;

import java.util.Map;
import java.util.Set;

import lombok.Getter;
import project.communication.model.Coin;


@Getter
public class WalletDto {
	String idWallet;
	String nameWallet;
	Map<CoinDto,Double> balance;
	

}
