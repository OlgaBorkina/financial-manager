package project.communication.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card implements Serializable{
	
	private static final long serialVersionUID = -4928139185368902603L;

	
	@Id
	Integer numberCard;
	
	Integer year;
	Integer month;
	
	String fullName;

}
