package project.rate.coin.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.rate.coin.service.RateCoinService;

@RestController
@RequiredArgsConstructor
public class RateCoinController {

	final RateCoinService rateService;
	
	@PostMapping("/coins/map")
	public boolean exchangeRatesCoin () throws URISyntaxException {
		return rateService.exchangeRatesCoin();
	}
	
	@PostMapping("platforms/all")
	public boolean allCoin () throws URISyntaxException {
		return rateService.allCoin();
	}
}
