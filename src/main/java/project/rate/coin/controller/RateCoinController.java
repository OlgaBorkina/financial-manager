package project.rate.coin.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.rate.coin.dto.CoinAdminDto;
import project.rate.coin.dto.CoinDto;
import project.rate.coin.model.AllCoin;
import project.rate.coin.service.RateCoinService;

@RestController
@RequiredArgsConstructor
public class RateCoinController {

	final RateCoinService rateService;
	
	@PostMapping("/coins/map")
	public boolean exchangeRatesCoin () throws URISyntaxException, IOException {
		return rateService.exchangeRatesCoin();
	}
	
	@PostMapping("/coins/list")
	public boolean allCoin () throws URISyntaxException {
		return rateService.allCoin();
	}
	
	@PostMapping("/coins/status/change")
	public Collection<String> changeStatusCoin(@RequestBody List<AllCoin> allStatus) throws IOException {
		return rateService.changeStatusCoin(allStatus);
	}
	
	@PostMapping("/allcoins/list")
	public Collection<CoinAdminDto> allCoinsToAdmin (){
		return rateService.allCoinsToAdmin();
	}
}
