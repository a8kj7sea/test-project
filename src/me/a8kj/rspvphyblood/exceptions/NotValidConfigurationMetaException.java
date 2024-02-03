package me.a8kj.rspvphyblood.exceptions;

public class NotValidConfigurationMetaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotValidConfigurationMetaException(String message) {
		super(message);
	}

	public NotValidConfigurationMetaException() {
		super("not vaild configuration , configuration class doesn't have valid configuration meta");
	}
}
