package com.vinicius.user_api.insfrastructure.exception;

import javax.naming.AuthenticationException;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message, Throwable throwable) {
        super(message,throwable);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
