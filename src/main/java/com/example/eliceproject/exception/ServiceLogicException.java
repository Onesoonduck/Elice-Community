package com.example.eliceproject.exception;

public class ServiceLogicException extends RuntimeException {
    public ServiceLogicException(String message) {
        super(message);
    }

    public ServiceLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
