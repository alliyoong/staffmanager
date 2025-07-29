package com.webapp.staffmanager.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.webapp.staffmanager.util.HttpResponse;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController{

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus status, String message) {
            return new ResponseEntity<>(
                    HttpResponse.builder()
                            .httpStatusCode(status.value())
                            .httpStatus(status)
                            .reason(status.getReasonPhrase().toUpperCase())
                            .message(message.toUpperCase())
                            .build(),
                    status
            );
        }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpResponse> resourceNotFoundException(ResourceNotFoundException e) {
        return createHttpResponse(NOT_FOUND, e.getMessage());
    }

}
