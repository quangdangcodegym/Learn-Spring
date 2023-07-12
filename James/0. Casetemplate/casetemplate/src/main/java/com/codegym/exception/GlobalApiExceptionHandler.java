package com.codegym.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(NumberInputExceptionApi.class)
    public ResponseEntity<?> dataInputException(NumberInputExceptionApi ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
