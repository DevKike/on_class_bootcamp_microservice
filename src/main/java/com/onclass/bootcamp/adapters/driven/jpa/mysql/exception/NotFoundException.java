package com.onclass.bootcamp.adapters.driven.jpa.mysql.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}