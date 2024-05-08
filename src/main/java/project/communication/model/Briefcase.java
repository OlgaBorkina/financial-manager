package project.communication.model;

import java.io.Serializable;
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
	Set <Card> cards;
	@OneToMany
	@JoinTable(
			name = "BRIEFCASE_WALLET",
			joinColumns = @JoinColumn(name = "BRIEFCASE_NAME"),
			inverseJoinColumns = @JoinColumn(name = "IDWALLET")
			)
	Set<Wallet> wallets;
	String managerName;

}
