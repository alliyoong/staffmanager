package com.webapp.staffmanager.util;

import org.springframework.stereotype.Service;

// @Service
public class TranslatorService {
    public String translate(String message){
        return Translator.toLocale(message);
    }
}
