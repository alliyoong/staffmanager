package com.webapp.staffmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private Object fieldValue;
    private String fieldName;
    private String resourceName;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s cannot be found with %s: %s", resourceName, fieldName, fieldValue.toString()));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}