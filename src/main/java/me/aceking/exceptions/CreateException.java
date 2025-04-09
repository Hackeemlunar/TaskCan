package me.aceking.exceptions;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class CreateException extends RuntimeException {
    public CreateException(String message) {
        super(message);
    }
}
