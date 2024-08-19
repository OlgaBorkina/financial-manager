package project.communication.dto;
import java.math.BigInteger;

import lombok.Getter;

@Getter
public class CardDto {
	
	BigInteger cardNumber;
	Integer cardYear;
	Integer cardMonth;
	String cardFullName;
	String currencyCode;

}
