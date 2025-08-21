package com.webapp.staffmanager.authentication.service.impl;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.entity.UserPrincipal;
import com.webapp.staffmanager.authentication.service.AccountService;
import com.webapp.staffmanager.authentication.service.AuthenticationService;
import com.webapp.staffmanager.authentication.service.JwtTokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountService accountService;
    private final AuthenticationManager appAuthenticationManager;
    private final JwtTokenService jwtService;
    private final PasswordEncoder encoder;

    @Override
    public Account register(Account data) {
        // if (!isEmailUnique(data.getEmail()))
        //     throw new ResourceAlreadyInUseException("account", "email", data.getEmail());
        // if (!isUserNameUnique(data.getUsername()))
        //     throw new ResourceAlreadyInUseException("account", "username", data.getUsername());
        // if (!isPasswordValid(data.getPassword()))
        //     throw new InvalidPasswordException();
        // copy data properties to a mess account
        Account toAdd = new Account();
        toAdd.setUsername(data.getUsername());
        toAdd.setPassword(encoder.encode(data.getPassword()));
        accountService.save(toAdd);
        return toAdd;
    }

    @Override
    public Map<String, Object> login(String username, String password, HttpServletResponse response,
            HttpServletRequest request) {
        appAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var loginAccount = accountService.findByUsername(username);
        UserPrincipal user = new UserPrincipal(loginAccount);
        var token = jwtService.generateAccessToken(user);

        return Map.of("token", token, "account", loginAccount);
    }

}
