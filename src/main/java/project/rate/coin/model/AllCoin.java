package project.rate.coin.model;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "coinCode")
@Table(name = "ALL_COINS1")
public class AllCoin  implements Serializable{
	
	private static final long serialVersionUID = -1617406424110188737L;
	
	@Id
	String coinCode;
	String coinName;
	Boolean used;
	
	
	public AllCoin(String coinCode, Boolean used) {
		this.coinCode = coinCode;
		this.used = used;
	}


	@Override
	public String toString() {
		return "AllCoin [coinCode=" + coinCode + ", coinName=" + coinName + ", used=" + used + "]";
	}
	
	
	
	

}
