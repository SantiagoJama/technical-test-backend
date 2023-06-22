package com.technicaltest.test.handler;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
