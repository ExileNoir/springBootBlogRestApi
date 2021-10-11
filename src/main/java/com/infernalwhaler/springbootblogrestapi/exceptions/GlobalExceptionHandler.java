package com.infernalwhaler.springbootblogrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Handle Exceptions globally
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 11/10/2021
 */

@ControllerAdvice
public class GlobalExceptionHandler {

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

}
