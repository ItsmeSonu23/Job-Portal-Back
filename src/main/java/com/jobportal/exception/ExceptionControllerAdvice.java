package com.jobportal.exception;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * Controller advice class that handles exceptions globally across the job portal application.
 * This class provides centralized exception handling for all controllers.
 *
 * @author Sonu Mishra
 * @version 1.0
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /** Environment object for accessing application properties */
    @Autowired
    private Environment environment;

    /**
     * Handles all uncaught exceptions in the application.
     * 
     * @param exception The uncaught exception
     * @return ResponseEntity containing error information with 500 status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalException(Exception exception) {
        ErrorInfo error = new ErrorInfo(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles job portal specific exceptions.
     * Retrieves error message from properties file using the exception message as key.
     * 
     * @param exception The JobPortalException that was thrown
     * @return ResponseEntity containing error information with 500 status code
     */
    @ExceptionHandler(JobPortalException.class)
    public ResponseEntity<ErrorInfo> generalException(JobPortalException exception) {
        String msg = environment.getProperty(exception.getMessage());
        ErrorInfo error = new ErrorInfo(msg, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles validation exceptions from both method arguments and constraints.
     * Concatenates all validation error messages into a single string.
     * 
     * @param exception Either MethodArgumentNotValidException or ConstraintViolationException
     * @return ResponseEntity containing error information with 400 status code
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception) {
        String msg = "";
        if(exception instanceof MethodArgumentNotValidException manvException){
            msg = manvException.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));

        }else{
            ConstraintViolationException cvException =  (ConstraintViolationException) exception;
            msg = cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        ErrorInfo error = new ErrorInfo(msg, HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
