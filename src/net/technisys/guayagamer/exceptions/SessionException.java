package net.technisys.guayagamer.exceptions;

public class SessionException extends Exception {

	private static final long serialVersionUID = -7828967584005678852L;

	private String message;

	public SessionException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
