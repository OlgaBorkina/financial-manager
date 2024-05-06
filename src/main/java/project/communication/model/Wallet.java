package project.communication.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "idWallet")
@Entity
public class Wallet implements Serializable {

	private static final long serialVersionUID = -1754810417262735488L;
	@Id
	String idWallet;

	String nameWallet;
	
//	@Column
//	@ElementCollection(targetClass = Double.class)
//	@Autowired
//	@Qualifier("balance")
	@ManyToMany (mappedBy = "")
	Map<Coin, Double> balance;

	
}
