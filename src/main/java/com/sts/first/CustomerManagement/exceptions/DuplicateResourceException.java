package com.sts.first.CustomerManagement.exceptions;
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
