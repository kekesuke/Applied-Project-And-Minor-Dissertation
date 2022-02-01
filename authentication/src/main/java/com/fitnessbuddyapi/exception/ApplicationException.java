package com.fitnessbuddyapi.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -430198416664624625L;

	public ApplicationException(String message) {
		super(message);
	}
}
