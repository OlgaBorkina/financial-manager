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
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "BNB_RATES")
public class CoinBnb extends CoinStandard {
	
	private static final long serialVersionUID = -6554811650097090650L;
	String code;
	
	
	public CoinBnb(LocalDateTime ratesDateTime, BigDecimal rate, BigInteger volume, BigInteger cap, Double Dhour,
			Double Dday, Double Dweek, Double Dmonth, Double Dquarter, Double Dyear, String code) {
		super(ratesDateTime, rate, volume, cap, Dhour, Dday, Dweek, Dmonth, Dquarter, Dyear);
		this.code = code;
	}
	
	
	
	

}
