package com.infernalwhaler.springbootblogrestapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Handle Exceptions globally
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles specific Resource not found Exception
     *
     * @param exc     of ResourceNotFound Object
     * @param request of WebRequest Object
     * @return ResponseEntity of ErrorDetails
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(final ResourceNotFoundException exc,
                                                                        final WebRequest request) {
        final ErrorDetails errorDetails =
                new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles specific Blog API Exception
     *
     * @param exc     of BlogAPI Object
     * @param request of WebRequest Object
     * @return ResponseEntity of ErrorDetails
     */
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiException(final BlogApiException exc,
                                                               final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles global Exceptions
     *
     * @param exc     of Exception Object
     * @param request of WebRequest Object
     * @return ResponseEntity  of ErrorDetails
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(final Exception exc, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders httpHeaders, final HttpStatus httpStatus, WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    final String fieldName = ((FieldError) error).getField();
                    final String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });

        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

}
