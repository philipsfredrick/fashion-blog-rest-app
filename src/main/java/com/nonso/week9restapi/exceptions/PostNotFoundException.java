package com.nonso.week9restapi.exceptions;

import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException{
    private String message;

    public PostNotFoundException(String message) {
        this.message = message;
    }
}
