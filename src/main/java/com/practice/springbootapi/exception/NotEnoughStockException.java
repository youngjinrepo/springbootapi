package com.practice.springbootapi.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughStockException extends HttpResponseException{

    public NotEnoughStockException(String msg) {
        super(msg);
    }

    public NotEnoughStockException(String msg, HttpStatus status) {
        super(status, msg);
    }
}
