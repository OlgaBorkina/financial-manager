package project.account.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import project.security.filter.enums.Role;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "login")
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable{

	private static final long serialVersionUID = -942555862539209457L;
	@Id
	String login;
	
	String firstName;
	
	String lastName;
	
	String email;
	
	String password;
	
	Integer phone;
	@Singular
	Set<Role> roles = new HashSet<>();

	public Account(String login, String firstName, String lastName, Set<String> roles) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		roles = new HashSet<>();

	}

	public boolean addRole(String role) {
		return roles.add(Role.valueOf(role.toUpperCase()));
	}

	public boolean removeRole(String role) {
		return roles.remove(role);
	}

	public Account(String login, String firstName, String lastName, String password) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
}
