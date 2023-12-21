package com.server.SpringTests.Exceptions;

public class UserNotFoundException extends ApiException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
