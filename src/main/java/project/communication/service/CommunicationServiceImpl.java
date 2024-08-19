package project.communication.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.boot.query.HbmResultSetMappingDescriptor.CollectionResultDescriptor;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import project.communication.dao.BriefcaseRepository;
import project.communication.dao.CardRepository;
import project.communication.dao.WalletRepository;
import project.communication.dto.BriefcaseDto;
import project.communication.dto.CoinBuyDto;
import project.communication.dto.MyCashDto;
import project.communication.dto.exceptions.BriefcaseNotFoundException;
import project.communication.dto.exceptions.CoinNotFoundException;
import project.communication.dto.exceptions.WallettNotFoundException;
import project.communication.model.Briefcase;
import project.communication.model.Card;
import project.communication.model.Wallet;
import project.rate.coin.dao.AllCoinRepository;
import project.rate.coin.dao.RateCoinRepository;
import project.rate.coin.model.AllCoin;
import project.rate.currency.dao.RateCurrencyRepository;


@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

	final BriefcaseRepository briefcaseRepository;
	final CardRepository cardRepository;
	final WalletRepository walletRepository;
	final AllCoinRepository allCoinRepository;
	final RateCurrencyRepository rateCurrencyRepository;
	final RateCoinRepository rateCoinRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public boolean createBriefcase(BriefcaseDto briefcaseDto) throws Exception {
		if (briefcaseRepository.existsById(briefcaseDto.getBriefcaseName())) {
			return false;
		}
		checkCodeInBase(briefcaseDto.getCurrencyCode());

		Set<Card> cards = briefcaseDto.getCards().stream().map(c -> cardRepository.save(new Card(c.getCardNumber(),
				c.getCardYear(), c.getCardMonth(), c.getCardFullName(), c.getCurrencyCode())))
				.collect(Collectors.toSet());

		Set<Wallet> wallets = briefcaseDto.getWallets().stream()
				.map(w -> walletRepository.findById(w.getIdWallet())
						.orElse(walletRepository.save(new Wallet(w.getIdWallet(), w.getNameWallet()))))
				.collect(Collectors.toSet());

		Briefcase briefcase = new Briefcase(briefcaseDto.getBriefcaseName(), cards, wallets,
				briefcaseDto.getManagerName(), 0., briefcaseDto.getCurrencyCode());
		briefcaseRepository.save(briefcase);
		return true;
	}

	@Override
	public BriefcaseDto changeBalanceInAccount(MyCashDto myCashDto) throws Exception {
		Briefcase briefcase = briefcaseRepository.findById(myCashDto.getNameCase())
				.orElseThrow(BriefcaseNotFoundException::new);

		Optional<Card> cardUsed = cardRepository.findByCardNumber(myCashDto.getCardNumber());
		checkCodeInBase(cardUsed.get().getCurrencyCode());
		checkCodeInBase(briefcase.getCurrencyCode());
		
		Double value = myCashDto.getChangeInAccount();
		BigDecimal result = new BigDecimal(value);
		result = result.setScale(2, RoundingMode.UP);

		if (cardUsed.get().getCurrencyCode() == briefcase.getCurrencyCode()) {
			sendToProcessing(cardUsed, result); // отправляем_в_процессингСкакой_карты_делать_снятие
			briefcase.addMyCash(myCashDto.getChangeInAccount());
		} else {
			Double res = convertValute(myCashDto.getChangeInAccount(), cardUsed.get().getCurrencyCode(),briefcase.getCurrencyCode());
			sendToProcessing(cardUsed, result); // отправляем_в_процессингСкакой_карты_делать_снятие
			briefcase.addMyCash(res);
		}
		briefcaseRepository.save(briefcase);
		return modelMapper.map(briefcase, BriefcaseDto.class);

	}

	@Override
	public Wallet buyCoin(CoinBuyDto coinBuyDto) throws Exception {
		Briefcase briefcase = briefcaseRepository.findById(coinBuyDto.getNameCase())
				.orElseThrow(BriefcaseNotFoundException::new);
		Set<Wallet> wallets = briefcase.getWallets();
		Wallet wallet = new Wallet(coinBuyDto.getIdWallet(), coinBuyDto.getNameCase(), null);

		AllCoin allCoin = allCoinRepository.findById(coinBuyDto.getNameCoin()).orElseThrow(CoinNotFoundException::new);
		if (!allCoin.getUsed()) {
			throw new Exception("The program does not work with this coin.");
		}
		
		
		String strCoinTable = coinBuyDto.getNameCoin().toUpperCase().concat("_RATES");
		Double rateCoinDouble = searchRateCoinInBase(strCoinTable);
		System.out.println(rateCoinDouble);
		Double needToBuyCoin = coinBuyDto.getQuantity()* rateCoinDouble;
		System.out.println(needToBuyCoin);
		if(!briefcase.getCurrencyCode().equalsIgnoreCase("USD") ) {
			Double currCode =  rateCurrencyRepository.checkCurrencyCode(LocalDateTime.now().minusHours(1), briefcase.getCurrencyCode());
			needToBuyCoin =  currCode * needToBuyCoin;
			System.out.println(needToBuyCoin +"евро");
		}
		Double checkInBal = briefcase.enoughMoneyInAccount(needToBuyCoin);
		if(checkInBal<0) {
			throw new  Exception("You do not have enough "+ checkInBal + briefcase.getCurrencyCode()+" in your account balance to purchase the coin "+ coinBuyDto.getNameCoin());
		}
		
		for (Wallet w : wallets) {
			if (w.getIdWallet().equals(coinBuyDto.getIdWallet())) {
				w.addCoinInBalance(coinBuyDto.getNameCoin(), coinBuyDto.getQuantity());
				wallet.setBalance(w.getBalance());
				briefcase.takeMyCash(needToBuyCoin);
				walletRepository.save(wallet);
				briefcaseRepository.save(briefcase); //??????
			} else {
				throw new WallettNotFoundException();
			}
		}

		return wallet;
	}
		
	
	

	private Double searchRateCoinInBase(String strCoinTable) throws SQLException, ClassNotFoundException {
		Class.forName("java.sql.Driver");  
		Connection con = DriverManager.getConnection("jdbc:h2:file:C:/project/projectDB", "", "");
		String ldtStr = LocalDateTime.now().minusDays(1).toString();

		Double myReturn = null;
		String query = "SELECT TOP 1 RATE  FROM " + strCoinTable + " WHERE RATES_DATE_TIME  > ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, ldtStr);
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
            myReturn = rs.getDouble("RATE");
            } 
		con.close();
		return myReturn;
	}
	
	
	
	
	
	
	

	@Override
	public Wallet saleCoin(CoinBuyDto coinBuyDto) throws Exception {
		System.out.println("111");
		Briefcase briefcase = briefcaseRepository.findById(coinBuyDto.getNameCase())
				.orElseThrow(BriefcaseNotFoundException::new);
		Set<Wallet> wallets = briefcase.getWallets();
		Wallet wallet = new Wallet(coinBuyDto.getIdWallet(), coinBuyDto.getNameCase(), null);

		for (Wallet w : wallets) {
			if (w.getIdWallet().equals(coinBuyDto.getIdWallet())) {
				w.saleCoinInBalance(coinBuyDto.getNameCoin(), coinBuyDto.getQuantity());
				wallet.setBalance(w.getBalance());
				walletRepository.save(wallet);
			} else {
				throw new WallettNotFoundException();
			}
		}
		return wallet;

	}

	private double convertValute(Double summ, String fromCode, String toCode) {
		return summ / rateCurrencyRepository.searchExchangeRate(LocalDateTime.now().minusHours(1), fromCode);
	}

	private void checkCodeInBase(String currencyCode) throws Exception {
		if (rateCurrencyRepository.checkCurrencyCode(LocalDateTime.now().minusDays(1), currencyCode) == null) {
			throw new Exception("This currency is not supported.");
		}
	}

	private void sendToProcessing(Optional<Card> cardUsed, BigDecimal result) {

	}
	
}
//if (rateCurrencyRepository.checkCurrencyCode(LocalDateTime.now().minusDays(1),
//myCashDto.getCurrencyCode()) == null) {
//throw new Exception("This currency is not supported.");
//}
//if (rateCurrencyRepository.checkCurrencyCode(LocalDateTime.now().minusDays(1),
//briefcase.getCurrencyCode()) == null) {
//throw new Exception("This currency is not supported.");
//}
