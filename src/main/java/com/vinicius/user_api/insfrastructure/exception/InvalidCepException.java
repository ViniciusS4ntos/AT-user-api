package com.vinicius.user_api.insfrastructure.exception;

public class InvalidCepException extends RuntimeException{
    public InvalidCepException(String message){super(message);}
    public InvalidCepException(String message, Throwable throwable){super(message,throwable);}
}
