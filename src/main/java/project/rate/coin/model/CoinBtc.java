package project.rate.coin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BTC_RATES")
public class CoinBtc  extends CoinStandard {

	private static final long serialVersionUID = 3904811683547683526L;
	
	
	String code;


	public CoinBtc(LocalDateTime ratesDateTime, BigDecimal rate, BigInteger volume, BigInteger cap, Double Dhour,
			Double Dday, Double Dweek, Double Dmonth, Double Dquarter, Double Dyear, String code) {
		super(ratesDateTime, rate, volume, cap, Dhour, Dday, Dweek, Dmonth, Dquarter, Dyear);
		this.code = code;
	}
	
	
	
	
	
	


	
	
	
	
	}


	
	
	
	
	


