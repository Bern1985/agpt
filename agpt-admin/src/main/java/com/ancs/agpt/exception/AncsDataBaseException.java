package com.ancs.agpt.exception;

public class AncsDataBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AncsDataBaseException(String message) {
        super(message);
    }

    public AncsDataBaseException(Throwable throwable) {
        super(throwable);
    }

    public AncsDataBaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

}