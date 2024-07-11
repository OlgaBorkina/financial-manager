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
@Table(name = "ALL_COINS")
public class AllCoin  implements Serializable{
	
	private static final long serialVersionUID = -1617406424110188737L;
	
	@Id
	String coinCode;
	String coinName;
	
	Boolean used;
	
	
	

}
