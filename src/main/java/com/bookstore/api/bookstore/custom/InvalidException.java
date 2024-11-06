package com.bookstore.api.bookstore.custom;

public class InvalidException extends RuntimeException {
    public InvalidException(String str) {
        super(str);
    }
}
