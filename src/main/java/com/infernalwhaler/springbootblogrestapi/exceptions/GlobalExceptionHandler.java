package com.infernalwhaler.springbootblogrestapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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
        final ErrorDetails errorDetails =
                new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));

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
    public ResponseEntity<ErrorDetails> handleGlobalException(final Exception exc,
                                                              final WebRequest request) {
        final ErrorDetails errorDetails =
                new ErrorDetails(LocalDateTime.now(), exc.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Handles non valid arguments
     *
     * @param exc         Exception to be thrown when validation on an argument annotated with @Valid fails
     * @param httpHeaders A data structure representing HTTP request or response headers
     * @param httpStatus  Enumeration of HTTP status codes.
     * @param request     Generic interface for a web request
     * @return ResponseEntity  of Object mapped Errors
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exc,
                                                                  final HttpHeaders httpHeaders,
                                                                  final HttpStatus httpStatus,
                                                                  final WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        exc.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    final String fieldName = ((FieldError) error).getField();
                    final String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
