package com.example.cleanarchtest.exception;

public class NetworkConnectionException extends Exception{
    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(final Throwable cause) {
        super(cause);
    }
}
