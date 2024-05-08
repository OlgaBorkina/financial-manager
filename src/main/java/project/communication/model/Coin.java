package project.communication.model;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "code")
@Entity
public class Coin {
	@Id
	String code;		//coin's code
	String name;		//coin's name
	String symbol;		//coin's symbol
	Integer rank;		//coin's rank
	Integer age	;	//coin's age in days
	String color;	//hexadecimal color code (#282a2a)
	String png32;	//32-pixel png image of coin icon
	String png64;	//64-pixel png image of coin icon
	String webp32;	//32-pixel webp image of coin icon
	String webp64;	//64-pixel webpg image of coin icon
	Integer exchanges;	//number of exchange coin is present at
	Integer markets;	//number of markets coin is present at
	Integer pairs;		//number of unique markets coin is present at
	Integer allTimeHighUSD	;		//all-time high in USD
	Integer circulatingSupply;		//number of coins minted, but not locked
	Integer totalSupply	;		//number of coins minted, including locked
	Integer maxSupply;		//maximum number of coins that can be minted
	Integer rate;		//price of coin in requested currency
	Integer volume;			//reported trading volume of the coin in last 24 hours in requested currency
	Integer cap	;		//coin's market cap in requested currency
	Integer liquidity;		//±2% orderbook depth
	Integer totalCap;		//	coin's hypothetical total capitalization at the moment
	
	
	
	@ElementCollection
    @CollectionTable(name = "coin_code_delta", 
      joinColumns = {@JoinColumn(name = "coin_code", referencedColumnName = "code")})
    @MapKeyColumn(name = "delta_name")
    @Column(name = "rate")
	Map<String, Double> delta; 
			//delta.hour	number	rate of change in the last hour
			//delta.day	number	rate of change in the last 24 hours
			//delta.week	number	rate of change in the last 7 days
			//delta.month	number	rate of change in the last 30 days
			//delta.quarter	number	rate of change in the last 90 days
			//delta.year
	
}
