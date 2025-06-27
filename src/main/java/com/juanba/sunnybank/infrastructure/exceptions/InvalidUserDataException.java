package com.juanba.sunnybank.infrastructure.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidUserDataException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity manageBadRequest(MethodArgumentNotValidException badRequestException) {
        var errors = badRequestException.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream()
                .map(DataErrorValidator::new)
                .toList());
    }

    public record DataErrorValidator(
            String field,
            String message
    ) {
        public DataErrorValidator(FieldError fieldError) {
            this(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }
    }
}
