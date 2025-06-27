package com.juanba.sunnybank.infrastructure.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidFieldRequestException {

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity invalidField(InvalidDefinitionException exception) {
        var error = exception.getMessage();
        return ResponseEntity.badRequest().body(new ResponseInvalidField("Invalid field on request", "Duplicate or overwritten field in the request"));
    }

    public record ResponseInvalidField(
            String typeError,
            String message
    ) {
        public ResponseInvalidField(String typeError, String message) {
            this.typeError = typeError;
            this.message = message;
        }
    }
}
