package com.webapp.staffmanager.staff.entity.dto;

import java.time.LocalDate;

import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.webapp.staffmanager.constant.ExceptionConstant.*;

public record StaffAddRequestDto(
    @NotEmpty(message = EMPTY_STAFF_NAME_ERROR_MSG, groups = OnUpdate.class)
    String name, 
    @NotEmpty(message = EMPTY_STAFF_EMAIL_ERROR_MSG, groups = OnUpdate.class)  
    @Email(message = INVALID_EMAIL_ERROR_MSG, groups = OnUpdate.class)
    String email, 
    @Pattern(regexp = "^\\+?[0-9]{9,13}$", message = INVALID_PHONE_NUMBER_ERROR_MSG, groups = OnUpdate.class)
    String phoneNumber, 
    LocalDate dateOfBirth,
    @NotNull(message = EMPTY_STAFF_GENDER_ERROR_MSG, groups = OnUpdate.class) 
    Gender gender, 
    @NotNull(message = EMPTY_STAFF_STATUS_ERROR_MSG, groups = OnUpdate.class) 
    StaffStatus status, 
    @NotNull(message = EMPTY_DEPARTMENT_ERROR_MSG, groups = OnUpdate.class)  
    int departmentId, 
    LocalDate joinDate
                                 ) {
    public interface OnUpdate{}
    public interface OnCreate{}
};
