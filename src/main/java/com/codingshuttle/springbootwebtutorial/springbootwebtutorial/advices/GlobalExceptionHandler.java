package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import org.hibernate.loader.ast.spi.CollectionLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//@RestControllerAdvice allows you to define global exception handlers that apply to all @RestController instances.
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourseNotFound(ResourceNotFoundException exception){
        APIError apiError=APIError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleInternalServerError(Exception exception){
        APIError apiError=APIError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors=exception.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toList());
        APIError apiError=APIError.builder().status(HttpStatus.BAD_REQUEST).message(errors.toString()).build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
