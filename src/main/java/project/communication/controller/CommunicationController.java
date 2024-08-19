package project.communication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import project.communication.dto.BriefcaseDto;
import project.communication.dto.CoinBuyDto;
import project.communication.dto.MyCashDto;
import project.communication.model.Wallet;
import project.communication.service.CommunicationService;

@RestController
@RequiredArgsConstructor
public class CommunicationController {

	final CommunicationService communicationService;

	@PostMapping("/{username}/briefcase")
	public boolean addBriefcase(@RequestBody BriefcaseDto briefcaseDto) throws Exception {
		return communicationService.createBriefcase(briefcaseDto);
	}

	@PostMapping("/{username}/briefcase/buycoin")
	public Wallet buyCoin(@RequestBody CoinBuyDto coinBuyDto) throws Exception {
		System.out.println("srt");
		return communicationService.buyCoin(coinBuyDto);
	}

	@PostMapping("/{username}/briefcase/salecoin")
	public Wallet saleCoin(@RequestBody CoinBuyDto coinBuyDto) throws Exception {
		return communicationService.saleCoin(coinBuyDto);
	}

	@PostMapping("/{username}/briefcase/cash")
	public BriefcaseDto changeBalanceInAccount(@RequestBody MyCashDto myCashDto) throws Exception {
		return communicationService.changeBalanceInAccount(myCashDto);
	}
}
