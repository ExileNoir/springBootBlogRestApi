package com.infernalwhaler.springbootblogrestapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource not found Exception
 *
 * @author sDeseure
 * @project springboot-blog-rest-api
 * @date 5/10/2021
 * <p>
 * Whenever a Post with a given id
 * is not found in the DB
 */

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class  ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
