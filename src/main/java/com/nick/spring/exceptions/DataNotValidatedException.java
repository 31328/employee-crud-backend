package com.nick.spring.exceptions;

public class DataNotValidatedException extends RuntimeException {
    // generate the following constructor from Super Class RuntimeException
    public DataNotValidatedException(String message) {
        super(message);
    }
}
