package ru.practicum.shareit.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> constraintException(final MethodArgumentTypeMismatchException e) {
        String error = "Unknown " + e.getName() + ": " + e.getValue();
        return ResponseEntity.status(400).body(new ErrorResponse(error));
    }

}

