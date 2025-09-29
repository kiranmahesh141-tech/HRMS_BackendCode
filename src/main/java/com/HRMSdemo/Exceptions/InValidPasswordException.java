package com.HRMSdemo.Exceptions;

public class InValidPasswordException extends RuntimeException {
    public InValidPasswordException(String message) {
        super(message);
    }
}
