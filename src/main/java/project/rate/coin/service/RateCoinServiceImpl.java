package project.rate.coin.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import project.rate.coin.dao.AllCoinRepository;
import project.rate.coin.dao.RateCoinRepository;
import project.rate.coin.dto.CoinDto;
import project.rate.coin.dto.RateCoinDto;
import project.rate.coin.model.AllCoin;
import project.rate.coin.model.CoinBnb;
import project.rate.coin.model.CoinBtc;
import project.rate.coin.model.CoinEth;



@Service
@RequiredArgsConstructor
public class RateCoinServiceImpl implements RateCoinService {

	final RateCoinRepository rateCoinRepository;
	final AllCoinRepository allCoinRepository;
	final ModelMapper modelMapper;

	@Override
//	@Scheduled(fixedRate = 60000)
	public boolean exchangeRatesCoin() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("x-api-key", "6f099acf-0c45-404c-a84d-7607f14dfc84");
		URI url = new URI("https://api.livecoinwatch.com/coins/list");

//		String bodyReq = "{\n\t\"currency\": \"USD\",\n\t\"sort\": \"rank\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 1,\n\t\"meta\": false\n}";
		String bodyReq = "{\n\t\"codes\": [\"ETH\",\"BTC\",\"BNB\"],\n\t\"currency\": \"USD\",\n\t\"sort\": \"code\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 10,\n\t\"meta\": false\n}";
		
		
		RequestEntity<String> request = new RequestEntity<String>(bodyReq, headers, HttpMethod.POST, url);
		ResponseEntity<RateCoinDto[]> response = restTemplate.exchange(request, RateCoinDto[].class);
		RateCoinDto[] responceBody = response.getBody();

//		System.out.println(responceBody[0].getCode());
//		System.out.println(responceBody[1].getCode());
//		System.out.println(responceBody[2].getCode());
		
	

		CoinBnb coinBnb = new CoinBnb(LocalDateTime.now(), responceBody[0].getRate(),
				responceBody[0].getVolume(), responceBody[0].getCap(),
				responceBody[0].getDelta().get("hour"), responceBody[0].getDelta().get("day"), responceBody[0].getDelta().get("week"),
				responceBody[0].getDelta().get("month"), responceBody[0].getDelta().get("quarter"),
				responceBody[0].getDelta().get("year"), responceBody[0].getCode());
		
		rateCoinRepository.save(coinBnb);
		
		CoinBtc coinBtc = new CoinBtc(LocalDateTime.now(), responceBody[1].getRate(),
				responceBody[1].getVolume(), responceBody[1].getCap(),
				responceBody[1].getDelta().get("hour"), responceBody[1].getDelta().get("day"), responceBody[1].getDelta().get("week"),
				responceBody[1].getDelta().get("month"), responceBody[1].getDelta().get("quarter"),
				responceBody[1].getDelta().get("year"), responceBody[1].getCode());
		
		rateCoinRepository.save(coinBtc);
		
		CoinEth coinEth = new CoinEth(LocalDateTime.now(), responceBody[2].getRate(),
				responceBody[2].getVolume(), responceBody[2].getCap(),
				responceBody[2].getDelta().get("hour"), responceBody[2].getDelta().get("day"), responceBody[2].getDelta().get("week"),
				responceBody[2].getDelta().get("month"), responceBody[2].getDelta().get("quarter"),
				responceBody[2].getDelta().get("year"), responceBody[2].getCode());
		
		rateCoinRepository.save(coinEth);

		return true;
	}
	@Override
	public boolean allCoin() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("x-api-key", "6f099acf-0c45-404c-a84d-7607f14dfc84");
		URI url = new URI("https://api.livecoinwatch.com/platforms/all");

		RequestEntity<String> request = new RequestEntity<String>(null, headers, HttpMethod.POST, url);
		ResponseEntity<CoinDto[]> response = restTemplate.exchange(request, CoinDto[].class);
		CoinDto[] resBody = response.getBody();
		
		for (int i = 0; i < resBody.length; i++) {
			
			AllCoin allCoin = new AllCoin(resBody[i].getCode(), resBody[i].getName(),false);
			allCoinRepository.save(allCoin);
		}

		
		return true;
	}
	
}