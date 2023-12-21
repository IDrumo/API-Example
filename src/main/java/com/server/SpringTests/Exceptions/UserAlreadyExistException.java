package com.server.SpringTests.Exceptions;

public class UserAlreadyExistException extends ApiException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
