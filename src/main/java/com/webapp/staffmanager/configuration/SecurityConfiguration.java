package com.webapp.staffmanager.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.webapp.staffmanager.filter.JwtAuthenticationFilter;

import static com.webapp.staffmanager.constant.SecurityConstant.PUBLIC_URLS;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

        // private final CustomAccessDeniedHandler accessDeniedHandler;
        // private final CustomForbiddenEntryPoint forbiddenEntryPoint;
        private final JwtAuthenticationFilter authenticationFilter;
        private final AuthenticationProvider authenticationProvider;

        @Value("${application.url.front-end}")
        private String frontEndUrl;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                                // .cors(Customizer.withDefaults())
                                // .cors(cors -> cors.disable())
                                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                                                request -> {
                                                        var cors = new CorsConfiguration();
                                                        cors.setAllowedHeaders(List.of("*"));
                                                        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE",
                                                                        "OPTIONS"));
                                                        cors.setAllowedOrigins(List.of(frontEndUrl, "*"));
                                                        cors.setExposedHeaders(List.of("Authorization",
                                                                        "Access-Control-Expose-Headers", "Jwt-Token"));
                                                        return cors;
                                                }))
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers(PUBLIC_URLS).permitAll()
                                                .anyRequest().authenticated())
                                .anonymous(httpSecurityAnonymousConfigurer -> httpSecurityAnonymousConfigurer.disable())
                                .authenticationProvider(authenticationProvider)
                                // .exceptionHandling((exception) -> exception
                                // .authenticationEntryPoint(forbiddenEntryPoint)
                                // .accessDeniedHandler(accessDeniedHandler))
                                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout
                                                .logoutUrl("/api/auth/logout")
                                                .addLogoutHandler((request, response, authentication) -> {
                                                        SecurityContextHolder.clearContext();
                                                })
                                                .logoutSuccessHandler((request, response, authentication) -> {
                                                        response.setStatus(HttpStatus.OK.value());
                                                        response.setHeader(HttpHeaders.CONTENT_TYPE,
                                                                        MediaType.APPLICATION_JSON_VALUE);
                                                        response.getWriter().write(
                                                                        "{\"message\":\"Logged out successfully\"}");
                                                })
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true));

                return http.build();
        }
}
