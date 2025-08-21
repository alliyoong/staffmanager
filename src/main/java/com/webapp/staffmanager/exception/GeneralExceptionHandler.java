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
import org.springframework.web.servlet.NoHandlerFoundException;

import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.util.HttpResponse;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GeneralExceptionHandler implements ErrorController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GeneralException.class)
    public HttpResponse generalException(GeneralException ex) {
        return HttpResponse.error(ex.getStatus());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // exception.getBindingResult().getFieldErrors().stream().forEach(System.out::println);
        List<String> errorMessages = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return HttpResponse.error(HttpStatus.BAD_REQUEST.value(),
                errorMessages.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
    }

    // Uncomment this if you want to handle constraint violations
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public HttpResponse
    // dataIntegrityViolationException(DataIntegrityViolationException exception){
    // var errorMessages = exception.getMostSpecificCause().getMessage();
    // return HttpResponse.error(HttpStatus.BAD_REQUEST.value(),
    // errorMessages);
    // }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public HttpResponse tokenInvalidException(JwtException exception) {
        return HttpResponse.error(AppResponseStatus.APP_404_ACCOUNT);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public HttpResponse noHandlerFoundException(NoHandlerFoundException ex) {
        return HttpResponse.error(AppResponseStatus.APP_404_URL);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HttpResponse internalServerException(Exception e) {
        // return HttpResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal
        // server error");
        return HttpResponse.error(AppResponseStatus.APP_500);
    }

}
