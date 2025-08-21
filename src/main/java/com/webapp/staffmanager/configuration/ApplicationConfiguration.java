package com.webapp.staffmanager.configuration;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.entity.UserPrincipal;
import com.webapp.staffmanager.authentication.repository.AccountRepository;
import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.constant.SecurityConstant;
import com.webapp.staffmanager.exception.GeneralException;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration{
    private final AccountRepository repository;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(SecurityConstant.BCRYPT_STRENGTH);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> {
            Account account = repository.findByUsername(username)
                    .orElseThrow(() -> new GeneralException(AppResponseStatus.APP_404_ACCOUNT));
            // validateLoginAttempt(account);
            account.setLastLoginDateDisplay(account.getLastLoginDate());
            account.setLastLoginDate(LocalDateTime.now());
            repository.save(account);
            return (UserDetails) new UserPrincipal(account);
        };
    }
    
    @Bean
    public AuthenticationProvider customProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager appAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
}