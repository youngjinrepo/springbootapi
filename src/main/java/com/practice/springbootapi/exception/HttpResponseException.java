package com.practice.springbootapi.exception;

import org.springframework.http.HttpStatus;

public class HttpResponseException extends RuntimeException{
    private HttpStatus status;

    public HttpResponseException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public HttpResponseException(String msg) {
        super(msg);
    }
}
