package project.communication.dto.exceptions;

public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = -2258498011251643496L;

	public InvalidInputException(String message) {
        super(message);
    }

}
