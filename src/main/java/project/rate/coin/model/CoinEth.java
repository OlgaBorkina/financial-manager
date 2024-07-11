package project.rate.coin.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ETH_RATES")
public class CoinEth extends CoinStandard {

	private static final long serialVersionUID = -2299154582235413203L;
	
	String code;
	
	
	public CoinEth(LocalDateTime ratesDateTime, BigDecimal rate, BigInteger volume, BigInteger cap, Double Dhour,
			Double Dday, Double Dweek, Double Dmonth, Double Dquarter, Double Dyear, String code) {
		
		super(ratesDateTime, rate, volume, cap, Dhour, Dday, Dweek, Dmonth, Dquarter, Dyear);
		this.code = code;
	}
	
	
	
	

}
