package project.rate.coin.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RateCoinDto {
	LocalDateTime date;
	String code;		
	BigDecimal rate;		
	BigInteger volume;			
	BigInteger cap	;	
	Map<String, Double> delta;
	
	
	
	@Override
	public String toString() {
		return "RateCoinDto [date=" + date + ", code=" + code + ", rate=" + rate + ", volume=" + volume + ", cap=" + cap
				+ ", delta=" + delta + "]";
	}
	
	
	
	
	
	
	

}
