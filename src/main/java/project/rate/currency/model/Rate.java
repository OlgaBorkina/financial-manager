package project.rate.currency.model;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "ratesDateTime")
@Entity
@Table(name = "rate")
public class Rate {
	@Id
//	@Column(name = "RATEDATETIME")
	LocalDateTime ratesDateTime;
	
	@ElementCollection(targetClass = Double.class)
	@CollectionTable(name = "date_valuta_tariff", 
    joinColumns = {@JoinColumn(name = "Rate", referencedColumnName = "ratesDateTime")})
    @MapKeyColumn(name = "currency_code")
    @Column(name = "tariff")
	Map<String, Double> rates = new HashMap<String, Double>();

	
	public Rate(LocalDateTime ratesDateTime, Map<String, Double> rates) {
		this.ratesDateTime = ratesDateTime;
		this.rates = rates;
	}
	
	public boolean checkCurrencyCode(String currencyCode) {
		return rates.containsKey(currencyCode);
	}
	
	
	
}
