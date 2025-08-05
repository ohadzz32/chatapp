package com.chatapp.chatapp.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException() {
        super();
    }

    public ChatNotFoundException(String message) {
        super(message);
    }

    public ChatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
