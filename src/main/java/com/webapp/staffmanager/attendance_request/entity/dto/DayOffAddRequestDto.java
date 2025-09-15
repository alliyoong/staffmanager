package com.webapp.staffmanager.attendance_request.entity.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DayOffAddRequestDto(
    @NotNull
    int staffId,

    String reason, 

    @NotNull
    LocalDate fromDate, 

    @NotNull
    LocalDate toDate
    ) {
    
}
