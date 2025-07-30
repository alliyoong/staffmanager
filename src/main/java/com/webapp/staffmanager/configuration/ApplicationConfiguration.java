package com.webapp.staffmanager.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ApplicationConfiguration{
    @Value("${application.defaultLocale}")
    private String appLocale;

    // @Bean
    // public LocaleResolver localeResolver() {
        // var resolver = new SessionLocaleResolver();
        // var defaultLocale = Locale.forLanguageTag(appLocale);
        // resolver.setDefaultLocale(defaultLocale);
        // return resolver;
    // }
    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new SessionLocaleResolver();
        var defaultLocale = Locale.forLanguageTag(appLocale);
        resolver.setDefaultLocale(defaultLocale);
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }   
}