package com.webapp.staffmanager.exception;

import com.webapp.staffmanager.constant.AppResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class GeneralException extends RuntimeException{
    private AppResponseStatus status;
    public GeneralException(AppResponseStatus status){
        this.status = status;
    }
}
