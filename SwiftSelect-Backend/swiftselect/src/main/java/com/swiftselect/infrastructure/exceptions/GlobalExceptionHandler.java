package com.swiftselect.infrastructure.exceptions;

import com.swiftselect.payload.response.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetails> handleApplicationException(ApplicationException exception,
                                                                   WebRequest webRequest) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(
                        new ErrorDetails(
                                new Date(),
                                exception.getMessage(),
                                webRequest.getDescription(false)
                        )
                );
    }
}
