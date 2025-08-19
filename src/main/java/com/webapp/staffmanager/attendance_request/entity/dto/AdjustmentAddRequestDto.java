package com.webapp.staffmanager.attendance_request.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.webapp.staffmanager.constant.RequestStatus;
import com.webapp.staffmanager.constant.RequestType;
import static com.webapp.staffmanager.constant.ExceptionConstant.*;

import jakarta.validation.constraints.NotNull;

public record AdjustmentAddRequestDto(

    @NotNull(message = EMPTY_TYPE_ERROR_MSG, groups = OnUpdate.class)
    RequestType type,
    @NotNull(message = EMPTY_STATUS_ERROR_MSG, groups = OnUpdate.class)
    RequestStatus status,
    LocalDateTime upDatedAt,
    @NotNull(message = EMPTY_STAFF_ERROR_MSG, groups = OnUpdate.class)
    int staffId,
    @NotNull(message = EMPTY_DATE_ERROR_MSG, groups = OnUpdate.class)
    LocalDate targetDate,
    @NotNull(message = EMPTY_CHECK_IN_TIME_ERROR_MSG, groups = OnUpdate.class)
    LocalDateTime newCheckInTime,
    @NotNull(message = EMPTY_CHECK_OUT_TIME_ERROR_MSG, groups = OnUpdate.class) 
    LocalDateTime newCheckOutTime,
    String reason
) {
    public interface OnUpdate{}
    public interface OnCreate{}
}
