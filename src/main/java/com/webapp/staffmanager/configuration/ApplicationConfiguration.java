package com.webapp.staffmanager.configuration;

import java.util.Locale;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.record.RecordModule;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class ApplicationConfiguration{
    @Value("${application.defaultLocale}")
    private String defaultLocale;

    @Value("${application.baseName}")
    private String baseName;

    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.of(defaultLocale));
        return resolver;
    }

    @Bean(name = "messages")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename(baseName);
        source.setDefaultEncoding("UTF-8");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }   
    
    // @Bean
    // public ModelMapper modelMapper() {
    //     ModelMapper modelMapper = new ModelMapper();
    //     modelMapper.getConfiguration().setAmbiguityIgnored(true);
    //     modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    //     modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    //     modelMapper.registerModule(new RecordModule());
    //     return modelMapper;
    // }
}