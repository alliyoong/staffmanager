package com.webapp.staffmanager.authentication.service;

import java.util.Map;

import com.webapp.staffmanager.authentication.entity.dto.RegisterRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    void register(RegisterRequestDto data);
    void editAccount(int id,RegisterRequestDto data);
    Map<String, Object> login(String username, String password, HttpServletResponse response, HttpServletRequest request);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
