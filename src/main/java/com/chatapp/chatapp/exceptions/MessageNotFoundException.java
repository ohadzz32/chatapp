package com.chatapp.chatapp.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException() {
        super();
    }

    public MessageNotFoundException(String message) {
        super(message);
    }

    public MessageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
