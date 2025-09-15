package com.webapp.staffmanager.authentication.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.repository.AccountRepository;
import com.webapp.staffmanager.authentication.entity.RefreshToken;
import com.webapp.staffmanager.authentication.repository.RefreshTokenRepository;
import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.exception.GeneralException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RefreshTokenService {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64encoder = Base64.getUrlEncoder();
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(AccountRepository accountRepository, RefreshTokenRepository refreshTokenRepository) {
        this.accountRepository = accountRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createAndSaveRefreshToken(String deviceName, int accountId) {
        var existedToken = refreshTokenRepository.findByDeviceNameAndAccountAccountId(deviceName, accountId);
        var tokenBuilder = RefreshToken.builder();
        if (existedToken.isPresent()) {
            tokenBuilder.refreshTokenId(existedToken.get().getRefreshTokenId());
        }
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        String tokenValue = base64encoder.encodeToString(randomBytes);
        Account tokenOwner = accountRepository.findById(accountId).orElseThrow();

        return refreshTokenRepository.save(tokenBuilder
                .token(tokenValue)
                .account(tokenOwner)
                .deviceName(deviceName)
                .expiration(Instant.now().plus(7, ChronoUnit.DAYS))
                .build());
    }
    
    public void deleteByAccountId(int accountId){
        refreshTokenRepository.deleteByAccountAccountId(accountId);
    }
    
    public void delete(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }
    
    // public RefreshToken findByAccountId(int id) {
    //     return refreshTokenRepository.findByAccountAccountId(id).orElse(null);
    // }
    
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(() -> new GeneralException(AppResponseStatus.APP_401));
    }
    
    public boolean isExpired(RefreshToken refreshToken){
        return refreshToken.getExpiration().isBefore(Instant.now());
    }
}
