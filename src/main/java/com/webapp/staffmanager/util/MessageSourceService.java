package com.webapp.staffmanager.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageSourceService {
    private final MessageSource messageSource;

    public String toLocale(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String toLocale(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
