package com.infernalwhaler.springbootblogrestapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Blog API Exception
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 8/10/2021
 */

@AllArgsConstructor
@Getter
public class BlogApiException extends RuntimeException {

    private final HttpStatus status;
    private final String message;


}
