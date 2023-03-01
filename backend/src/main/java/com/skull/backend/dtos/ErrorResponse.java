package com.skull.backend.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private int code;
    private HttpStatus status;
    private String message;
    private String location;
}
