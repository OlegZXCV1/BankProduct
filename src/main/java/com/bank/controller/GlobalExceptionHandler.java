package com.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * It handles exceptions thrown by the controllers and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles UnsupportedOperationException.
   *
   * @param ex the exception
   * @return a response entity with the exception message and a BAD_REQUEST status
   */
  @ExceptionHandler(UnsupportedOperationException.class)
  public ResponseEntity<String> handleUnsupportedOperation(UnsupportedOperationException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }

  /**
   * Handles IllegalArgumentException.
   *
   * @param ex the exception
   * @return a response entity with the exception message and a BAD_REQUEST status
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }

  /**
   * Handles IllegalStateException.
   *
   * @param ex the exception
   * @return a response entity with the exception message and a BAD_REQUEST status
   */
  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }
}
