package project.rate.coin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "ratesDateTime")
@MappedSuperclass
public class CoinStandard implements Serializable {
	
	private static final long serialVersionUID = -8981278541530941427L;
	
	@Id
	LocalDateTime ratesDateTime;
	BigDecimal rate;		
	BigInteger volume;		
	BigInteger cap	;	
	Double Dhour;
	Double Dday;
	Double Dweek;
	Double Dmonth;
	Double Dquarter;
	Double Dyear;
	
	
	
	@Override
	public String toString() {
		return "RateCoinFull [ratesDateTime=" + ratesDateTime + ", rate=" + rate + ", volume=" + volume + ", cap=" + cap
				+ ", Dhour=" + Dhour + ", Dday=" + Dday + ", Dweek=" + Dweek + ", Dmonth=" + Dmonth + ", Dquarter="
				+ Dquarter + ", Dyear=" + Dyear + "]";
	}
	
	
	

}
