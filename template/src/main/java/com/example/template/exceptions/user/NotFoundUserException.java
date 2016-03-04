package com.example.template.exceptions.user;

public class NotFoundUserException extends RuntimeException {

	private static final long serialVersionUID = 3315407810446789379L;
	
	public NotFoundUserException(String id) {
		super("Can't not found " + id);
	}
}
