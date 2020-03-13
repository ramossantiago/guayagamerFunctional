package net.technisys.guayagamer.exceptions;

public class InvalidArgumentsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public InvalidArgumentsException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
