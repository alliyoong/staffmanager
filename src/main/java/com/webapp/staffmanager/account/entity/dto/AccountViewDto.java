package com.webapp.staffmanager.account.entity.dto;

import java.time.LocalDateTime;


import com.webapp.staffmanager.constant.AccountStatus;

public record AccountViewDto(
    String username, 
    String password,
    AccountStatus accountStatus,
    LocalDateTime lastLoginDateDisplay,
    LocalDateTime createdAt
    ) {
    
}
