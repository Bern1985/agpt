package com.ancs.agpt.exception;

public class AncsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AncsException(String message) {
        super(message);
    }

    public AncsException(Throwable throwable) {
        super(throwable);
    }

    public AncsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}