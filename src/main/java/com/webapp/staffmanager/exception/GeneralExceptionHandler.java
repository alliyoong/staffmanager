package com.webapp.staffmanager.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.webapp.staffmanager.util.HttpResponse;

@RestControllerAdvice
public class GeneralExceptionHandler implements ErrorController{

    @ExceptionHandler(GeneralException.class)
    public HttpResponse generalException(GeneralException ex){
        return HttpResponse.error(ex.getStatus());
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return HttpResponse.error(HttpStatus.BAD_REQUEST.value(), 
                                    errorMessages.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
    }

    @ExceptionHandler(Exception.class)
    public HttpResponse internalServerException(Exception e) {
        return HttpResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
    }

}
