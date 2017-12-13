package com.ancs.agpt.exception;

public class AncsPageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AncsPageException(String message) {
        super(message);
    }

    public AncsPageException(Throwable throwable) {
        super(throwable);
    }

    public AncsPageException(String message, Throwable throwable) {
        super(message, throwable);
    }

}