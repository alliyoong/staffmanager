package com.webapp.staffmanager.authentication.service.impl;

import java.util.Map;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.repository.AccountRepository;
import com.webapp.staffmanager.account.service.AccountService;
import com.webapp.staffmanager.authentication.entity.RefreshToken;
import com.webapp.staffmanager.authentication.entity.UserPrincipal;
import com.webapp.staffmanager.authentication.service.AuthenticationService;
import com.webapp.staffmanager.authentication.service.JwtTokenService;
import com.webapp.staffmanager.authentication.service.RefreshTokenService;
import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.entity.mapper.StaffMapper;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager appAuthenticationManager;
    private final JwtTokenService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final StaffMapper staffMapper;

    // @Transactional
    // @Override
    // public void register(RegisterRequestDto data) {
    // Staff toAddStaff = accountMapper.toStaff(data);
    // log.info("Staff info: {}",toAddStaff.toString());
    // var added = staffService.saveToDb(toAddStaff);

    // Account toAddAccount = accountMapper.toAccount(data);
    // log.info("Account info: {}",toAddAccount.toString());
    // toAddAccount.setPassword(encoder.encode(toAddAccount.getPassword()));
    // toAddAccount.setStaff(added);
    // toAddAccount.setAccountStatus(AccountStatus.ENABLED);
    // accountService.saveToDb(toAddAccount);
    // }

    @Transactional
    @Override
    public Map<String, Object> login(String username,
            String password,
            HttpServletResponse response,
            HttpServletRequest request) {
        appAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var loginAccount = accountRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(AppResponseStatus.APP_404_ACCOUNT));
        // Account acc = accountService.findWithStaff(username);
        UserPrincipal user = new UserPrincipal(loginAccount);
        var accessToken = jwtService.generateAccessToken(user);
        var dto = staffMapper.fromAccount(loginAccount);

        var refreshToken = refreshTokenService.createAndSaveRefreshToken(request.getHeader("User-Agent"),
                loginAccount.getAccountId());

        ResponseCookie refreshTokenCookie = generateCookie(refreshToken);
        return Map.of("access-token", accessToken, "staff", dto, "refresh-token", refreshTokenCookie.toString());
    }

    public void blackList(String token) {
        jwtService.blackList(token);
    }

    public void revokeRefreshTokenForUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(AppResponseStatus.APP_404_ACCOUNT));

        refreshTokenService.deleteByAccountId(account.getAccountId());

        SecurityContextHolder.clearContext();
    }

    public ResponseCookie clearCookies() {
        return generateCookie(null);
    }

    @Override
    @Transactional
    public Map<String, String> refreshToken(String accessToken, String refreshToken) {
        var toCheckRefresh = refreshTokenService.findByToken(refreshToken);
        // check refresh token is expired
        if (refreshTokenService.isExpired(toCheckRefresh)) {
            throw new GeneralException(AppResponseStatus.APP_401);
        }
        // check access token is blacklisted
        if (jwtService.isTokenBlackListed(accessToken)) {
            throw new GeneralException(AppResponseStatus.APP_401);
        }
        // check user of access token and refresh token
        var accessUsername = jwtService.getSubjectIgnoreExpiration(accessToken);
        var refreshUsername = toCheckRefresh.getAccount().getUsername();
        if (!accessUsername.equals(refreshUsername)) {
            throw new GeneralException(AppResponseStatus.APP_401);
        }
        var currentSignedInAccount = accountRepository.findByUsername(refreshUsername).get();
        var currentUserPrincipal = new UserPrincipal(currentSignedInAccount);
        // generate new refresh token and access token
        var newRefreshToken = refreshTokenService.createAndSaveRefreshToken(toCheckRefresh.getDeviceName(), toCheckRefresh.getAccount().getAccountId());
        var newAccessToken = jwtService.generateAccessToken(currentUserPrincipal);
        return Map.of("access-token", newAccessToken, "refresh-cookie", generateCookie(newRefreshToken).toString());
    }

    private ResponseCookie generateCookie(RefreshToken refreshToken) {
        long ttl;
        String token;
        if (null != refreshToken) {
            ttl = refreshToken.getExpiration().toEpochMilli() - System.currentTimeMillis();
            token = refreshToken.getToken();
        } else {
            ttl = 0;
            token = "";
        }
        return ResponseCookie.from("refresh-token", token)
                .httpOnly(true)
                .secure(false) // true if use https
                // .path("/api/auth/refresh")
                .path("/")
                .maxAge(ttl)
                .sameSite("Lax")
                .build();
    }
}
