package ru.kpfu.itis.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}