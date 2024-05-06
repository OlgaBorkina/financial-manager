package project.communication.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "nameCase")
@Entity
public class Briefcase implements Serializable{
	
	private static final long serialVersionUID = 7192590693656603147L;
	@Id
	String nameCase;
	@OneToMany
	Set <Card> cards;
	@OneToMany
	Set<Wallet> wallets;
	String nameManager;

}
