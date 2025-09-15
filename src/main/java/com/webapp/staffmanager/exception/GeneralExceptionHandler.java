package com.webapp.staffmanager.exception;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.webapp.staffmanager.util.MessageSourceService;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler implements ErrorController {
    private final MessageSourceService messageSource;

    public GeneralExceptionHandler(MessageSourceService messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GeneralException.class)
    public HttpResponse generalException(GeneralException ex) {
        String localizedMessage = messageSource.toLocale(ex.getStatus().getCode());
        log.info("General exception: {}", ex.getStatus().getCode());
        return HttpResponse.error(ex.getStatus().getCode(), localizedMessage);
    }

    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public HttpResponse
    // methodArgumentNotValidException(MethodArgumentNotValidException exception) {
    // //
    // exception.getBindingResult().getFieldErrors().stream().forEach(System.out::println);
    // List<String> errorMessages = exception
    // .getBindingResult()
    // .getFieldErrors()
    // .stream()
    // .map(DefaultMessageSourceResolvable::getDefaultMessage)
    // .collect(Collectors.toList());
    // return HttpResponse.error(HttpStatus.BAD_REQUEST.value(),
    // errorMessages.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
    // }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String[] args = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField())
                .toArray(String[]::new);

        var localizedMessage = messageSource.toLocale(AppResponseStatus.APP_400_FIELD.getCode(), args);

        return HttpResponse.error(AppResponseStatus.APP_400_FIELD.getCode(), localizedMessage);
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
        return HttpResponse.error(AppResponseStatus.APP_401);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public HttpResponse noHandlerFoundException(NoHandlerFoundException ex) {
        var localizedMessage = messageSource.toLocale(AppResponseStatus.APP_404_URL.getCode());
        return HttpResponse.error(AppResponseStatus.APP_404_URL.getCode(), localizedMessage);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public HttpResponse internalServerException(Exception e) {
        // return HttpResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal
        // server error");
        // log.info("Internal server error: {}", e.getCause());
        var localizedMessage = messageSource.toLocale(AppResponseStatus.APP_500.getCode());
        return HttpResponse.error(AppResponseStatus.APP_500.getCode(), localizedMessage);
    }

}
