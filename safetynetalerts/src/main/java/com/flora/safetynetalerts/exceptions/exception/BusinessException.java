package com.flora.safetynetalerts.exceptions.exception;

public class BusinessException extends RuntimeException{

    public BusinessException() {}

    public BusinessException(String msg)
    {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
