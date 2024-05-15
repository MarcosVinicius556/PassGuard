package com.devmarcos.passguard.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devmarcos.passguard.services.exceptions.DatabaseException;
import com.devmarcos.passguard.services.exceptions.ResourceNotFoundException;
import com.devmarcos.passguard.services.exceptions.UserAlreadySavedException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler extends RuntimeException {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError responseError = new StandardError.Builder()
                                                       .setTimestamp(Instant.now())
                                                       .setStatus(status.value())
                                                       .setError(error)
                                                       .setMessage(exception.getMessage())
                                                       .setPath(request.getRequestURI())
                                                       .build();
        return ResponseEntity.status(status).body(responseError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException exception, HttpServletRequest request) {
        String error = "Bad Request.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError responseError = new StandardError.Builder()
                                                       .setTimestamp(Instant.now())
                                                       .setStatus(status.value())
                                                       .setError(error)
                                                       .setMessage(exception.getMessage())
                                                       .setPath(request.getRequestURI())
                                                       .build();
        return ResponseEntity.status(status).body(responseError);
    }

    @ExceptionHandler(UserAlreadySavedException.class)
    public ResponseEntity<StandardError> userAlreadySavedException(UserAlreadySavedException exception, HttpServletRequest request) {
        String error = "Conflict.";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError responseError = new StandardError.Builder()
                                                       .setTimestamp(Instant.now())
                                                       .setStatus(status.value())
                                                       .setError(error)
                                                       .setMessage(exception.getMessage())
                                                       .setPath(request.getRequestURI())
                                                       .build();
        return ResponseEntity.status(status).body(responseError);
    }


}
