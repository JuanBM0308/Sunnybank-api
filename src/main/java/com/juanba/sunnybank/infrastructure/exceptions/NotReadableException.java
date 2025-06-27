package com.juanba.sunnybank.infrastructure.exceptions;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotReadableException {

    @ExceptionHandler(NotReadablePropertyException.class)
    public ResponseEntity notReadableError400(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
