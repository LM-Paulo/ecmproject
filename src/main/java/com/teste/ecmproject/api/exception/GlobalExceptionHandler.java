package com.teste.ecmproject.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException ex) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), "Business Exception");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
