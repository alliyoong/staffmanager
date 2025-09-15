package com.webapp.staffmanager.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.webapp.staffmanager.authentication.entity.RefreshToken;

import jakarta.transaction.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Modifying
    @Transactional
    void deleteByAccountAccountId(int accountId);
    Optional<RefreshToken> findByAccountAccountId(int id);
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByDeviceNameAndAccountAccountId(String deviceName, int id);
}
