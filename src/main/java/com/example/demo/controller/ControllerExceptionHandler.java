package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

/** Interceptador de exceções não tratadas nos controllers */
@ControllerAdvice @Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> responseStatusExceptionHandler(Exception ex) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        String msg = "Erro inesperado no servidor";
        final var error = new ErrorMessage(msg, status);
        log.error(msg, ex);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> responseStatusExceptionHandler(ResponseStatusException ex) {
        final var errorMessage = new ErrorMessage(ex.getReason(), ex.getStatusCode());
        return new ResponseEntity<>(errorMessage, ex.getStatusCode());
    }
}