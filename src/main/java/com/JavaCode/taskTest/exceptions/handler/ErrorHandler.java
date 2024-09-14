package com.JavaCode.taskTest.exceptions.handler;

import com.JavaCode.taskTest.exceptions.NotFoundFundsException;
import com.JavaCode.taskTest.exceptions.NotFoundWalletException;
import com.JavaCode.taskTest.exceptions.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundWalletException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel notFound(RuntimeException e) {
        return ErrorModel.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.toString())
                .build();
    }

    @ExceptionHandler({NotFoundFundsException.class,
            HttpMessageNotReadableException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel badRequest(RuntimeException e) {
        return ErrorModel.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel valid(MethodArgumentNotValidException e) {
        return ErrorModel.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .build();
    }
}
