package com.Citronix.Citronix.exception.handler;

import com.Citronix.Citronix.entityError.ErrorMessage;
import com.Citronix.Citronix.exception.EntityInvalidFarmException;
import com.Citronix.Citronix.exception.EntityInvalidFieldArea;
import com.Citronix.Citronix.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .code(404)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EntityInvalidFarmException.class})
    public ResponseEntity<Object> entityInvalidFarmException(EntityInvalidFarmException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .code(204)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {EntityInvalidFieldArea.class})
    public ResponseEntity<Object> entityInvalidFieldArea(EntityInvalidFieldArea ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .code(204)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NO_CONTENT);
    }

}
