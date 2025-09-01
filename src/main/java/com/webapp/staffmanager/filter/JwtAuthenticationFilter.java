package com.webapp.staffmanager.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.webapp.staffmanager.authentication.service.JwtTokenService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import static com.webapp.staffmanager.constant.SecurityConstant.*;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwt;
    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationFilter(JwtTokenService jwt,
            @Qualifier("errorAttributes") HandlerExceptionResolver resolver) {
        this.jwt = jwt;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            try {
                String token = authHeader.substring(TOKEN_PREFIX.length()).trim();
                // log.info("TOKEN: {}", token);
                if (jwt.isValid(token)
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Authentication authentication = jwt.getAuthentication(token);

                    var newContext = SecurityContextHolder.createEmptyContext();
                    newContext.setAuthentication(authentication);
                    SecurityContextHolder.setContext(newContext);

                } else {
                    // SecurityContextHolder.clearContext();
                }
            } catch (JwtException e) {
                resolver.resolveException(request, response, null, e);
            }
        }
        filterChain.doFilter(request, response);
    }

}
