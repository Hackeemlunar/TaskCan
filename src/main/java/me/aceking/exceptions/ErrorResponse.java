package me.aceking.exceptions;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getters
    public String getMessage() {
        return message;
    }
}