package com.HRMSdemo.Exceptions;

import java.util.HashMap;
import java.util.Map;
 
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
@RestControllerAdvice
public class GlobalExceptionHandler {
 
    private ResponseEntity<Map<String, String>> buildError(HttpStatus status, String message, String field) {
        Map<String, String> error = new HashMap<>();
        error.put("status", "error");
        error.put("message", message);
        if (field != null) {
            error.put("field", field);
        }
        return ResponseEntity.status(status).body(error);
    }
 
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmail(DuplicateEmailException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), "email");
    }
 
    @ExceptionHandler(DuplicatePhoneException.class)
    public ResponseEntity<Map<String, String>> handleDuplicatePhone(DuplicatePhoneException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), "phone");
    }
 
    @ExceptionHandler(InValidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPassword(InValidPasswordException ex) {
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage(), "password");
    }
 
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), "identifier");
    }
 
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateException(DataIntegrityViolationException ex) {
    String message = "Duplicate entry detected!";
    String field = null;

    Throwable rootCause = ex.getRootCause();
    String rootMessage = rootCause != null ? rootCause.getMessage() : ex.getMessage();

    if (rootMessage != null) {
        if (rootMessage.toLowerCase().contains("email")) {   // case-insensitive
            message = "Email already exists!";
            field = "email";
        } else if (rootMessage.toLowerCase().contains("phone")) {
            message = "Phone already exists!";
            field = "phone";
        }
    }

    return buildError(HttpStatus.CONFLICT, message, field);
}

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<Map<String, String>> handleUserNotActive(UserNotActiveException ex) {
        return buildError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), "status");
    }
 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong: " + ex.getMessage(), null);
    }
}


