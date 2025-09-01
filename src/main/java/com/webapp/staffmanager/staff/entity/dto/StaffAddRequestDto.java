package com.webapp.staffmanager.staff.entity.dto;

import java.time.LocalDate;

import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.util.validator.AppNotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.webapp.staffmanager.constant.ExceptionConstant.*;

public record StaffAddRequestDto(
    @NotBlank(message = EMPTY_NAME_ERROR_MSG, groups = OnUpdate.class)
    String name, 
    @NotBlank(message = EMPTY_EMAIL_ERROR_MSG, groups = OnUpdate.class)  
    @Email(message = INVALID_EMAIL_ERROR_MSG, groups = OnUpdate.class)
    String email, 
    @Pattern(regexp = "^\\+?[0-9]{9,13}$", message = INVALID_PHONE_NUMBER_ERROR_MSG, groups = OnUpdate.class)
    String phoneNumber, 
    @Pattern(regexp = "^\\+?[0-9]{9,13}$", message = INVALID_SOCIAL_NUMBER_ERROR_MSG, groups = OnUpdate.class)
    String socialSecurityNumber, 
    LocalDate dateOfBirth,
    @NotNull(message = EMPTY_GENDER_ERROR_MSG, groups = OnUpdate.class) 
    Gender gender, 
    @NotNull(message = EMPTY_STATUS_ERROR_MSG, groups = OnUpdate.class) 
    StaffStatus status, 
    @NotNull(message = EMPTY_DEPARTMENT_ERROR_MSG, groups = OnUpdate.class)  
    int departmentId, 
    LocalDate joinDate
                                 ) {
    public interface OnUpdate{}
    public interface OnCreate{}
};
