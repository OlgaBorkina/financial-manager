package project.account.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7576572795662993957L;

	

	
}
