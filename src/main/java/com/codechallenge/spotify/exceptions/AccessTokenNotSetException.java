package com.codechallenge.spotify.exceptions;

public class AccessTokenNotSetException extends RuntimeException {

    public AccessTokenNotSetException(String message) {
        super(message);
    }
}
