package com.webapp.staffmanager.authentication.restcontroller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.entity.dto.LoginRequestDto;
import com.webapp.staffmanager.authentication.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.authentication.service.AuthenticationService;
import com.webapp.staffmanager.constant.SecurityConstant;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.util.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api/auth")
public class AuthenticationRestController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public HttpResponse login(
            @Validated @RequestBody(required = true) LoginRequestDto loginRequest,
            @NonNull HttpServletResponse response,
            @NonNull HttpServletRequest request) throws IOException {

        Map<String, Object> result = authenticationService.login(
                loginRequest.username(),
                loginRequest.password(),
                response,
                request);
        response.setHeader(HttpHeaders.AUTHORIZATION, SecurityConstant.TOKEN_PREFIX + result.get("token"));
        // response.setHeader("Access-Control-Expose-Headers", "Jwt-Token");

        return HttpResponse.ok(result.get("staff"));
    }

    @PostMapping("/register")
    public HttpResponse register(
            @RequestBody RegisterRequestDto data
            ) throws IOException {
        authenticationService.register(data);

        return HttpResponse.created();
    }

    @PutMapping("/edit-account/{id}")
    public HttpResponse edit(
            @RequestBody RegisterRequestDto data,
            @PathVariable("id") int id
            ) throws IOException {
        log.info("Editing account with data: {}", data);
        authenticationService.editAccount(id, data);
        return HttpResponse.created();
    }

    // @PostMapping("/logout")
    // public HttpResponse logout(@RequestHeader("Authorization") String authHeader)
    // {
    // String token = authHeader.replace("Bearer ", "");
    // log.info("Logging out user with token: {}", token);

    // // todo - possibly do blacklist token
    // return HttpResponse.ok("Logged out successfully");
    // }
}
