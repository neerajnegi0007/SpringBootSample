package com.example.demo.controllers.exceptions;

public class UserNotFoundException extends Throwable {

	public UserNotFoundException(String string) {
		super(string);
	}
	
}
