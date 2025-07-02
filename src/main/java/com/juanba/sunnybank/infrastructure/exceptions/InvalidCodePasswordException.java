package com.juanba.sunnybank.infrastructure.exceptions;

import org.springframework.http.ResponseEntity;

public class InvalidCodePasswordException extends IllegalArgumentException {

    private final String detail;

    public InvalidCodePasswordException(String detail) {
        this.detail = detail;
    }

    public ResponseEntity invalidCodeError() {
        return ResponseEntity.badRequest().body(new ResponseInvalidCode("error verification code", detail));
    }

    public record ResponseInvalidCode(
            String typeError,
            String message
    ) {
        public ResponseInvalidCode(String typeError, String message) {
            this.typeError = typeError;
            this.message = message;
        }
    }
}
