package project.communication.service;

import project.communication.dto.BriefcaseDto;
import project.communication.dto.CoinBuyDto;
import project.communication.dto.MyCashDto;
import project.communication.model.Wallet;

public interface CommunicationService {
	
	boolean createBriefcase (BriefcaseDto briefcaseDto ) throws Exception;
	
	Wallet buyCoin(CoinBuyDto coinBuyDto) throws Exception;

	Wallet saleCoin(CoinBuyDto coinBuyDto) throws Exception;

	BriefcaseDto changeBalanceInAccount(MyCashDto myCashDto) throws Exception;

	
	

}
