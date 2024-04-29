package project.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import project.account.model.Account;



public interface AccountRepository  extends JpaRepository<Account, String>{
		
}
