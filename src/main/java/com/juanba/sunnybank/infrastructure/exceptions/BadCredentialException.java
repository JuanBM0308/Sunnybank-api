package com.juanba.sunnybank.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadCredentialException {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseInvalidCredentials("Invalid credentials", "The email or password does not match our registered users"));
    }

    public record ResponseInvalidCredentials(
            String typeError,
            String message
    ) {
        public ResponseInvalidCredentials(String typeError, String message) {
            this.typeError = typeError;
            this.message = message;
        }
    }
}
