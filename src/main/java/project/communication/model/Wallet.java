package project.communication.model;

import java.io.Serializable;
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

@AllArgsConstructor
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
	Map<String, Double> balance;
}
