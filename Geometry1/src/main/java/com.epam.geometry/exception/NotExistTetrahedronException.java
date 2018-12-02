package com.epam.geometry.exception;

public class NotExistTetrahedronException extends Exception{
    public NotExistTetrahedronException() { super(); }
    public NotExistTetrahedronException(String message) { super(message); }
    public NotExistTetrahedronException(String message, Throwable cause) { super(message, cause); }
    public NotExistTetrahedronException(Throwable cause) { super(cause); }
    protected NotExistTetrahedronException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
