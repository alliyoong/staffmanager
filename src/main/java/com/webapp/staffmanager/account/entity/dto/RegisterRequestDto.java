package com.webapp.staffmanager.account.entity.dto;

import com.webapp.staffmanager.constant.AccountStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RegisterRequestDto(
    @NotBlank(groups = OnCreate.class)
    String username, 

    @NotBlank(groups = OnCreate.class)
    String password,

    @NotNull(groups = OnCreate.class)
    AccountStatus accountStatus,

    @NotNull(groups = OnCreate.class)
    int staffId

) {
    public interface OnUpdate{}
    public interface OnCreate{}
}
