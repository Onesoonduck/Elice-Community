package com.example.eliceproject.exception;

import lombok.Getter;

@Getter
public class ServiceLogicException extends RuntimeException {

    private ExceptionCode exceptionCode;
    public ServiceLogicException(String message) {
        super(message);
    }

    public ServiceLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
