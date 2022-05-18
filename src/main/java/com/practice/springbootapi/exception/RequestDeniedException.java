package com.practice.springbootapi.exception;

import org.springframework.http.HttpStatus;

public class RequestDeniedException extends HttpResponseException {

    public RequestDeniedException(HttpStatus status, String msg) {
        super(status, msg);
    }

    public RequestDeniedException(String msg) {
        super(msg);
    }
}
