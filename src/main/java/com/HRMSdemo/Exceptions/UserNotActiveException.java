package com.HRMSdemo.Exceptions;

public class UserNotActiveException extends RuntimeException{

	public UserNotActiveException(String message) {
		super(message);
	}
	
}
