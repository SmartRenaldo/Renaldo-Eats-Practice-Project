package com.renaldo.common;

/**
 * This custom exception will be used with Global Exception Handler to handle custom exception
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
