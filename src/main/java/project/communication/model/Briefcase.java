package project.communication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Briefcase implements Serializable{
	
	private static final long serialVersionUID = 7192590693656603147L;
	@Id
	String briefcaseName;
	@OneToMany
	@JoinTable(
			name = "BRIEFCASE_CARD",
			joinColumns = @JoinColumn(name = "BRIEFCASE_NAME"),
			inverseJoinColumns = @JoinColumn(name = "CARDNUMBER")
			)
	Set <Card> cards = new HashSet<Card>();
	@OneToMany
	@JoinTable(
			name = "BRIEFCASE_WALLET",
			joinColumns = @JoinColumn(name = "BRIEFCASE_NAME"),
			inverseJoinColumns = @JoinColumn(name = "IDWALLET")
			)
//	@JsonManagedReference
	Set<Wallet> wallets = new HashSet<Wallet>();
	String managerName;
	Double myCash;
	String currencyCode;
	
	public Briefcase(String briefcaseName, Set<Card> cards, Set<Wallet> wallets, String managerName) {
		this.briefcaseName = briefcaseName;
		this.cards = cards;
		this.wallets = wallets;
		this.managerName = managerName;
	}
	
	public boolean addMyCash(Double plusToMyCash) {
		myCash += plusToMyCash;
		return true;
		
	}
	public Double enoughMoneyInAccount (Double summ) {
		if(myCash-summ>0) {
			return summ;
		}else {
			return myCash-summ;
		}
	}
	
	public boolean takeMyCash (Double minusFromMyCash) {
		myCash = myCash - minusFromMyCash;
		return false;
	}
	

}
