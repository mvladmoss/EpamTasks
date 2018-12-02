package com.epam.geometry.exception;

public class IncorrectInputDataException extends Exception {
    public IncorrectInputDataException() { super(); }
    public IncorrectInputDataException(String message) { super(message); }
    public IncorrectInputDataException(String message, Throwable cause) { super(message, cause); }
    public IncorrectInputDataException(Throwable cause) { super(cause); }
    protected IncorrectInputDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }}
