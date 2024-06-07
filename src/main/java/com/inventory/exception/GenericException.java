package com.inventory.exception;

import com.inventory.dto.ErrerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericException extends RuntimeException{
    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericException(Throwable cause) {
        super(cause);
    }

    public GenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Object> exceptionHandling(GenericException genericException){
        return  new ResponseEntity<>(new ErrerDto(genericException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
