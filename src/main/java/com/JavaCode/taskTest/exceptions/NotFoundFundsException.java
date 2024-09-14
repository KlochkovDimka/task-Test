package com.JavaCode.taskTest.exceptions;

public class NotFoundFundsException extends RuntimeException{

    public NotFoundFundsException(String message) {
        super(message);
    }
}
