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

import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.util.HttpResponse;

@RestControllerAdvice
public class GeneralExceptionHandler implements ErrorController{

    // private HttpResponse createHttpResponse(AppResponseStatus status) {
            // return HttpResponse.builder()
                            // .httpStatusCode(status.value())
                            // .httpStatus(status)
                            // .message(message.toUpperCase())
                            // .build(),
                    // status
            // );
        // }

    @ExceptionHandler(GeneralException.class)
    public HttpResponse generalException(GeneralException ex){
        return HttpResponse.error(ex.getStatus());
    }
    // public ResponseEntity<HttpResponse> resourceNotFoundException(ResourceNotFoundException e) {
        // return createHttpResponse(NOT_FOUND, e.getMessage());
    // }
    //
    // @ExceptionHandler(ResourceAlreadyInUseException.class)
    // public ResponseEntity<HttpResponse> resourceAlreadyInUseException(ResourceAlreadyInUseException e) {
        // return createHttpResponse(BAD_REQUEST, e.getMessage());
    // }
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(IllegalArgumentException.class)
    // public HttpResponse resourceAlreadyInUse(){
        // return HttpResponse.error(AppResponseStatus.APP_400);
    // }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        // return createHttpResponse(BAD_REQUEST, errorMessages.toString()
                // .replaceAll("\\[", "").replaceAll("\\]", ""));
        return HttpResponse.error(HttpStatus.BAD_REQUEST.value(), 
                                    errorMessages.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
    }

}
