package com.practice.springbootapi.exception;

public class NotEnoughStockException extends RuntimeException{


    public NotEnoughStockException(String msg) {
        super(msg);
    }
}
