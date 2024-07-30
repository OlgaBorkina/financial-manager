package project.rate.coin.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.core.ContextNamingInfoListener;
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
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;
import project.rate.coin.dao.AllCoinRepository;
import project.rate.coin.dao.RateCoinRepository;
import project.rate.coin.dto.CoinAdminDto;
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
	public boolean exchangeRatesCoin() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("x-api-key", "6f099acf-0c45-404c-a84d-7607f14dfc84");
		URI url = new URI("https://api.livecoinwatch.com/coins/list");

//		String bodyReq = "{\n\t\"currency\": \"USD\",\n\t\"sort\": \"rank\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 1,\n\t\"meta\": false\n}";
		
		String partBody = coinFromFile();
//		System.out.println(partBody);
		String bodyReq = "{\n\t\"codes\": [\"ETH\",\"BTC\",\"BNB\"],\n\t\"currency\": \"USD\",\n\t\"sort\": \"code\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 10,\n\t\"meta\": false\n}";
		String bodyReq1 = "{\n\t\"codes\": " + partBody + ",\n\t\"currency\": \"USD\",\n\t\"sort\": \"code\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 10,\n\t\"meta\": false\n}";
		
		//		String bodyReq1 = "\"{\n\t\"codes\": " + partBody + ",\n\t\"currency\": \"USD\",\n\t\"sort\": \"code\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 10,\n\t\"meta\": false\n}\"";
		
		
//		System.out.println(bodyReq);
//		System.out.println(bodyReq1);

		RequestEntity<String> request = new RequestEntity<String>(bodyReq1, headers, HttpMethod.POST, url);
		ResponseEntity<RateCoinDto[]> response = restTemplate.exchange(request, RateCoinDto[].class);
		RateCoinDto[] responceBody = response.getBody();

//		System.out.println(responceBody[0].getCode());
//		System.out.println(responceBody[1].getCode());
//		System.out.println(responceBody[2].getCode());
		
		for (int i = 0; i < responceBody.length; i++) {
			System.out.println(responceBody[i].getCode());
			switch (responceBody[i].getCode()) {
			
		case "BNB": {
			
			CoinBnb coinBnb = new CoinBnb(LocalDateTime.now(), responceBody[i].getRate(), responceBody[i].getVolume(),
					responceBody[i].getCap(), responceBody[0].getDelta().get("hour"), responceBody[i].getDelta().get("day"),
					responceBody[i].getDelta().get("week"), responceBody[i].getDelta().get("month"),
					responceBody[i].getDelta().get("quarter"), responceBody[i].getDelta().get("year"),
					responceBody[i].getCode());

			rateCoinRepository.save(coinBnb);
			break;
		}
		
		case "BTC" : {
			CoinBtc coinBtc = new CoinBtc(LocalDateTime.now(), responceBody[i].getRate(), responceBody[i].getVolume(),
					responceBody[i].getCap(), responceBody[i].getDelta().get("hour"), responceBody[i].getDelta().get("day"),
					responceBody[i].getDelta().get("week"), responceBody[i].getDelta().get("month"),
					responceBody[i].getDelta().get("quarter"), responceBody[i].getDelta().get("year"),
					responceBody[i].getCode());
	
			rateCoinRepository.save(coinBtc);
			break;
		}
		
		case "ETH" :{
			CoinEth coinEth = new CoinEth(LocalDateTime.now(), responceBody[i].getRate(), responceBody[i].getVolume(),
					responceBody[i].getCap(), responceBody[i].getDelta().get("hour"),
					responceBody[i].getDelta().get("day"), responceBody[i].getDelta().get("week"),
					responceBody[i].getDelta().get("month"), responceBody[i].getDelta().get("quarter"),
					responceBody[i].getDelta().get("year"), responceBody[i].getCode());

			rateCoinRepository.save(coinEth);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + responceBody[i].getCode());
		}
		}
		
		
//
//		CoinBnb coinBnb = new CoinBnb(LocalDateTime.now(), responceBody[0].getRate(), responceBody[0].getVolume(),
//				responceBody[0].getCap(), responceBody[0].getDelta().get("hour"), responceBody[0].getDelta().get("day"),
//				responceBody[0].getDelta().get("week"), responceBody[0].getDelta().get("month"),
//				responceBody[0].getDelta().get("quarter"), responceBody[0].getDelta().get("year"),
//				responceBody[0].getCode());
//
//		rateCoinRepository.save(coinBnb);

//		CoinBtc coinBtc = new CoinBtc(LocalDateTime.now(), responceBody[1].getRate(), responceBody[1].getVolume(),
//				responceBody[1].getCap(), responceBody[1].getDelta().get("hour"), responceBody[1].getDelta().get("day"),
//				responceBody[1].getDelta().get("week"), responceBody[1].getDelta().get("month"),
//				responceBody[1].getDelta().get("quarter"), responceBody[1].getDelta().get("year"),
//				responceBody[1].getCode());
//
//		rateCoinRepository.save(coinBtc);
//
//		CoinEth coinEth = new CoinEth(LocalDateTime.now(), responceBody[2].getRate(), responceBody[2].getVolume(),
//				responceBody[2].getCap(), responceBody[2].getDelta().get("hour"), responceBody[2].getDelta().get("day"),
//				responceBody[2].getDelta().get("week"), responceBody[2].getDelta().get("month"),
//				responceBody[2].getDelta().get("quarter"), responceBody[2].getDelta().get("year"),
//				responceBody[2].getCode());
//
//		rateCoinRepository.save(coinEth);

		return true;
	}

	@Override
	public boolean allCoin() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("x-api-key", "6f099acf-0c45-404c-a84d-7607f14dfc84");
		URI url = new URI("https://api.livecoinwatch.com/coins/list");

		String bodyReq = "{\n\t\"currency\": \"USD\",\n\t\"sort\": \"rank\",\n\t\"order\": \"ascending\",\n\t\"offset\": 0,\n\t\"limit\": 100,\n\t\"meta\": true\n}";
		RequestEntity<String> request = new RequestEntity<String>(bodyReq, headers, HttpMethod.POST, url);
		ResponseEntity<CoinDto[]> response = restTemplate.exchange(request, CoinDto[].class);
		CoinDto[] resBody = response.getBody();

		for (int i = 0; i < resBody.length; i++) {

			AllCoin allCoin = new AllCoin(resBody[i].getCode(), resBody[i].getName(), false);
			allCoinRepository.save(allCoin);
		}
		return true;
	}

	@Override
	public Collection<CoinAdminDto> allCoinsToAdmin() {
		return allCoinRepository.findAll().stream().map(c -> modelMapper.map(c, CoinAdminDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public Collection<String> changeStatusCoin(List<AllCoin> allStatus) throws IOException {
		Map<String, Boolean> allStatusMap = allStatus.stream()
				.collect(Collectors.toMap(AllCoin::getCoinCode, AllCoin::getUsed));
		List<AllCoin> allCoins = allCoinRepository.findAll();
		List<String> coinStatus = new ArrayList<>();

		for (AllCoin allCoin : allCoins) {
			if (allStatusMap.containsKey(allCoin.getCoinCode())) {
				boolean status = allStatusMap.get(allCoin.getCoinCode());
				allCoin.setUsed(status);
				allCoinRepository.save(allCoin);
				if (status) {
					coinStatus.add(allCoin.getCoinCode());
				}
			}
		}
		coinToFile(coinStatus);
		return coinStatus;
	}

//	public FileWriter coinToFile (List<String> coinStatus) throws IOException {
//		FileWriter writer = new FileWriter("coinStatus2.txt"); 
//		for(String str: coinStatus) {
//		  writer.write(str + System.lineSeparator());
//		}
//		writer.close();
//		return writer;
//	}

	public BufferedWriter coinToFile(List<String> coinStatus) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("coinStatus3.txt"));
		for (String str : coinStatus) {
			writer.write(str + System.lineSeparator());
		}
		writer.close();
		return writer;
	}

	public String coinFromFile() throws IOException {
		
		File file = new File("coinStatus3.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String  res = "[\"";
		String line;
		while ((line = br.readLine()) != null) {
			res += line;
			res = res + "\",\"" ;
//			System.out.println(res);
		}
		res = res.substring(0, res.length()-3);
		res =res+ "\"]";
//		System.out.println(res);
		br.close();
		fr.close();
	//	[\"ETH\",\"BTC\",\"BNB\"]
		
		return res;
	}

}

