package com.swiftmart.swmartproductserv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
    Global Exception Handler to manage common exceptions across the application.
*/

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles the common NullPointerException, for robustness of the application ,and avoids exposing stack traces to clients.
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 Status
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        // Log the exception details for debugging (this is important!// TODO)
        // logger.error("An unexpected null pointer exception occurred", ex);

        // Return a clean, user-friendly error response
        return new ResponseEntity<>("An internal server error occurred due to missing data.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
