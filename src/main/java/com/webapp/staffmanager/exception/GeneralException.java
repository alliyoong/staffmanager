package com.webapp.staffmanager.exception;

import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.util.TranslatorService;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GeneralException extends RuntimeException{
    private AppResponseStatus status;
    // private String code;
    // private String message;
    // private final TranslatorService service;
    public GeneralException(AppResponseStatus status){
        // this.code = status.getCode();
        // this.message = service.translate(service.translate(message));
        this.status = status;
    }
}
