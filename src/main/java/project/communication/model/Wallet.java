package project.communication.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "idWallet")
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

	private static final long serialVersionUID = 196942693178048187L;
	@Id
	@Column(name = "IDWALLET")
	String idWallet;
	String nameWallet;
	
	
	@ElementCollection(targetClass = Double.class)
    @CollectionTable(name = "wallet_coin_sum", 
    joinColumns = {@JoinColumn(name = "Wallet", referencedColumnName = "idWallet")})
    @MapKeyColumn(name = "coin_code")
    @Column(name = "sum")
	Map<String, Double> balance	= new HashMap<String, Double>();


	public Wallet(String idWallet, String nameWallet) {
		this.idWallet = idWallet;
		this.nameWallet = nameWallet;
	}


	public Wallet(String idWallet, String nameWallet, Map<String, Double> balance) {
		this.idWallet = idWallet;
		this.nameWallet = nameWallet;
		this.balance =  balance;
	}
	
	public boolean coinBye(String coinCode, double sum) {
		return balance.put(coinCode, null) == null;
	}


	public Wallet(String idWallet, Map<String, Double> balance) {
		this.idWallet = idWallet;
		this.balance = balance;
	}
	
	public boolean cheсkCoin(String coinName) {
		return balance.containsKey(coinName);
	}
	
	public boolean addCoinInBalance(String coinName, Double quantity) {
		if(!cheсkCoin(coinName)) {
			balance.put(coinName,quantity);
		}else {
			Double old = getBalance().get(coinName);
			balance.put(coinName, old+quantity);
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
}
