package com.webapp.staffmanager.attendance.entity.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.webapp.staffmanager.constant.AttendanceStatus;
import static com.webapp.staffmanager.constant.ExceptionConstant.*;

import jakarta.validation.constraints.NotNull;

public record AttendanceAddRequestDto(

    @NotNull(message = EMPTY_STAFF_ERROR_MSG, groups = {OnCreate.class, OnUpdate.class})
    int staffId,
    @NotNull(message = EMPTY_DATE_ERROR_MSG, groups = {OnCreate.class, OnUpdate.class})
    LocalDate workDate,
    @NotNull(message = EMPTY_STATUS_ERROR_MSG, groups = {OnCreate.class, OnUpdate.class})
    AttendanceStatus status,
    @NotNull(message = EMPTY_CHECK_IN_TIME_ERROR_MSG, groups = {OnCreate.class, OnUpdate.class})
    LocalTime checkInTime,
    LocalTime checkOutTime
) {
    public interface OnUpdate{}
    public interface OnCreate{}
}
