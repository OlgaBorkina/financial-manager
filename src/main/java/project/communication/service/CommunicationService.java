package project.communication.service;

import java.util.List;
import java.util.Set;

import project.communication.dto.BriefcaseDto;
import project.communication.dto.CoinBuyDto;
import project.communication.model.Wallet;

public interface CommunicationService {
	
	boolean createBriefcase (BriefcaseDto briefcaseDto );
	
	Wallet coinBuy(CoinBuyDto coinBuyDto);
	

}
