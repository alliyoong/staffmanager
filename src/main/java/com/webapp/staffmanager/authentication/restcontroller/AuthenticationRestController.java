package com.webapp.staffmanager.authentication.restcontroller;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.authentication.entity.dto.LoginRequestDto;
import com.webapp.staffmanager.authentication.service.AuthenticationService;
import com.webapp.staffmanager.constant.SecurityConstant;
import com.webapp.staffmanager.util.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public HttpResponse login(
            @RequestBody(required = true) LoginRequestDto loginRequest,
            @NonNull HttpServletResponse response,
            @NonNull HttpServletRequest request) throws IOException {

        Map<String, Object> result = authenticationService.login(
                loginRequest.username(),
                loginRequest.password(),
                response,
                request);
        response.setHeader(HttpHeaders.AUTHORIZATION, SecurityConstant.TOKEN_PREFIX + result.get("token"));
        // response.setHeader("Access-Control-Expose-Headers", "Jwt-Token");

        return HttpResponse.ok(result.get("account"));
    }
}
