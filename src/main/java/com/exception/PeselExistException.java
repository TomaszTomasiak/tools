package com.exception;

public class PeselExistException extends Throwable {
    public PeselExistException(final String message) {
        super(message);
    }
}
