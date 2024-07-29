package project.rate.coin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoinAdminDto {
	String coinCode;
	String coinName;
	Boolean used;
	
	
	@Override
	public String toString() {
		return "CoinAdminDto [coinCode=" + coinCode + ", coinName=" + coinName + ", used=" + used + "]";
	}
	
	
	
	
	
	

}
