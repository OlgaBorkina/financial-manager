package project.communication.service;

import java.util.Set;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import project.communication.dao.BriefcaseRepository;
import project.communication.dao.CardRepository;
import project.communication.dao.WalletRepository;
import project.communication.dto.BriefcaseDto;
import project.communication.dto.CoinBuyDto;
import project.communication.dto.exceptions.BriefcaseNotFoundException;
import project.communication.dto.exceptions.WallettNotFoundException;
import project.communication.model.Briefcase;
import project.communication.model.Card;
import project.communication.model.Wallet;


@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

	final BriefcaseRepository briefcaseRepository;
	final CardRepository cardRepository;
	final WalletRepository walletRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public boolean createBriefcase(BriefcaseDto briefcaseDto) {
		if (briefcaseRepository.existsById(briefcaseDto.getNameCase())) {
			return false;
		}

		Set<Card> cards = briefcaseDto.getCards().stream()
				.map(c -> cardRepository
						.save(new Card(c.getCardNumber(), c.getCardYear(), c.getCardMonth(), c.getCardFullName())))
				.collect(Collectors.toSet());

		Set<Wallet> wallets = briefcaseDto.getWallets().stream()
				.map(w -> walletRepository.findById(w.getIdWallet())
						.orElse(walletRepository.save(new Wallet(w.getIdWallet(), w.getNameWallet()))))
				.collect(Collectors.toSet());

		Briefcase briefcase = new Briefcase(briefcaseDto.getNameCase(), cards, wallets, briefcaseDto.getNameManager());
		briefcaseRepository.save(briefcase);
		return true;
	}

	
	@Override
	public Wallet coinBuy(CoinBuyDto coinBuyDto) {
		Briefcase briefcase = briefcaseRepository.findById(coinBuyDto.getNameCase()).orElseThrow(BriefcaseNotFoundException::new);
		Set<Wallet> wallets = briefcase.getWallets();
		Wallet wallet = new Wallet(coinBuyDto.getIdWallet(),coinBuyDto.getNameCase(), null);
//		
//		if (!true) { // проверка о прапвильном коине
//			throw new WallettNotFoundException();
//		} 
		
//			wallets.stream().filter( w -> w.equals(coinBuyDto.getIdWallet())).forEach(w -> new Wallet(w.getIdWallet(),w.getBalance()));
			for (Wallet w : wallets) {
				if (w.getIdWallet().equals(coinBuyDto.getIdWallet())) {
					w.addCoinInBalance(coinBuyDto.getNameCoin(), coinBuyDto.getQuantity());
					wallet.setBalance(w.getBalance());
					walletRepository.save(wallet);
				}else {
					throw new WallettNotFoundException();
				}
			}

		return wallet;

	}

}

