package com.Library.library_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<String> handleNotFound(
	            ResourceNotFoundException ex) {

	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(ex.getMessage());
	    }

	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<String> handleBadRequest(
	            IllegalArgumentException ex) {

	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(ex.getMessage());
	    }
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationErrors(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	        });

	        return ResponseEntity.badRequest().body(errors);
	    }
	    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	    public ResponseEntity<String> handleOptimisticLocking(
	            ObjectOptimisticLockingFailureException ex) {

	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("This book was updated by another request. Please try again.");
	    }
	    
}
