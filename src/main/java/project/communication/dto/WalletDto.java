package project.communication.dto;

import java.util.Map;
import lombok.Getter;

@Getter
public class WalletDto {
	String idWallet;
	String nameWallet;
	Map<String,Double> balance;
	

}
