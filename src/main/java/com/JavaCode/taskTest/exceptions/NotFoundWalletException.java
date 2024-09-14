package com.JavaCode.taskTest.exceptions;

public class NotFoundWalletException extends RuntimeException {

    public NotFoundWalletException(String message) {
        super(message);
    }
}
