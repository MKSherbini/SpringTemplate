package com.skull.backend.exceptions.handlers;

import com.skull.backend.dtos.ErrorResponse;
import com.skull.backend.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(ResourceNotFoundException ex, HttpServletRequest req) {
        String message = String.format("resource with id:%s is not found", ex.getId());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.valueOf(400));
        errorResponse.setCode(400);
        errorResponse.setLocation(req.getRequestURL().toString());

        return new ResponseEntity<>(errorResponse,
                errorResponse.getStatus());
    }
}
