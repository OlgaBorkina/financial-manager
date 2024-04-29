package project.account.dto.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)

public class AlreadyExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1309932715823766990L;
	
	
	

}
