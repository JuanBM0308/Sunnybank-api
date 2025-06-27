package com.juanba.sunnybank.infrastructure.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class InternalServerErrorException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity internalServerError(Exception exception) {
        return ResponseEntity.internalServerError().body(new ResponseInternalServerError("Server error 500-01", exception.getLocalizedMessage(), List.of("(Fake) Contact the SunnyBank Support Team at HelpDesk@sunnybank.com", "(Fake) International line 018000475763", "(Fake) Report the error at www.sunny-help-online-assessor.com")));
    }

    public record ResponseInternalServerError(
            String typeError,
            String message,
            List<String> recommendations
    ) {
        public ResponseInternalServerError(String typeError, String message, List<String> recommendations) {
            this.typeError = typeError;
            this.message = message;
            this.recommendations = recommendations;
        }
    }
}
