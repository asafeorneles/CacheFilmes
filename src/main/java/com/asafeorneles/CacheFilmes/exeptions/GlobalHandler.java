package com.asafeorneles.CacheFilmes.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundExceptionHandler(ResourceNotFoundExceptions ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(
                        HttpStatus.NOT_FOUND.name(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        ex.getDeveloperMessage(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(ConflictBusinessException.class)
    public ResponseEntity<ExceptionResponse> conflictBusinessExceptionHandler(ConflictBusinessException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(
                        HttpStatus.CONFLICT.name(),
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage(),
                        ex.getDeveloperMessage(),
                        LocalDateTime.now()
                )
        );
    }
}
