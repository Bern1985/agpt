package com.ancs.agpt.exception;

public class ParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(Throwable throwable) {
        super(throwable);
    }

    public ParameterException(String message, Throwable throwable) {
        super(message, throwable);
    }

}