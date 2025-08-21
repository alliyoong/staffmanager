package com.webapp.staffmanager.authentication.service;

import java.util.Map;

import com.webapp.staffmanager.authentication.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    Account register(Account account);
    Map<String, Object> login(String username, String password, HttpServletResponse response, HttpServletRequest request);
}
