package project.rate.coin.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import project.rate.coin.dto.CoinAdminDto;
import project.rate.coin.dto.CoinDto;
import project.rate.coin.model.AllCoin;



public interface RateCoinService {
	
	boolean exchangeRatesCoin() throws URISyntaxException, IOException;

	boolean allCoin() throws URISyntaxException;

	Collection<String> changeStatusCoin(List<AllCoin> allStatus) throws IOException;

	Collection<CoinAdminDto> allCoinsToAdmin();

	
}
