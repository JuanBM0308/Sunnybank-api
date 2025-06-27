package com.juanba.sunnybank.infrastructure.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EndPointForbiddenException {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity forbiddenExceptionError() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseForbiddenError("Protected end-point", "You don't have the required permits for this path"));
    }

    public record ResponseForbiddenError(
            String typeError,
            String message
    ) {
        public ResponseForbiddenError(String typeError, String message) {
            this.typeError = typeError;
            this.message = message;
        }
    }
}
