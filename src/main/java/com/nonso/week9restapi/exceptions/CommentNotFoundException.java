package com.nonso.week9restapi.exceptions;

public class CommentNotFoundException extends RuntimeException {

    private String message;

    public CommentNotFoundException(String message) {
        this.message = message;
    }
}
