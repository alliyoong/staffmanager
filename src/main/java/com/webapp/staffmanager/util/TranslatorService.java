package com.webapp.staffmanager.util;

// @Service
public class TranslatorService {

    private static TranslatorService instance;
    private TranslatorService() {}

    public static TranslatorService getInstance() {
        if (instance == null) {
            instance = new TranslatorService();
        }
        return instance;
    }

    public String translate(String message){
        return Translator.toLocale(message);
    }

}
