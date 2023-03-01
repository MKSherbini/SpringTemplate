package com.skull.backend.exceptions;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException {
    @Getter
    private final int id;

    public ResourceNotFoundException(int id) {
        super();
        this.id = id;
    }
}
