package com.sparksupport.sparktraining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNullValueException(NullValueException ex){
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, "Null Value Exception");
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(RuntimeException ex){
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, "Invalid Request");
    }

    @ExceptionHandler({ResourceNotFoundException.class, CategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND) // Good practice to identify the type of status in web
    public ErrorResponse handleNotFoundException(RuntimeException ex){
        return new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "Resource Not Found",
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), "Handling in Global : an error occurred", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public ErrorResponse buildErrorResponse(RuntimeException ex, HttpStatus status, String error){
        return new ErrorResponse(LocalDateTime.now(),
                ex.getMessage(),
                error,
                status.value()
        );
    }
}