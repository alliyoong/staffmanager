package com.webapp.staffmanager.authentication.entity.dto;

import java.time.LocalDate;

import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import static com.webapp.staffmanager.constant.ExceptionConstant.*;

public record RegisterRequestDto(
    @NotBlank(groups = OnUpdate.class)
    String username, 

    @NotBlank(groups = OnUpdate.class)
    String password,

    @NotBlank(groups = OnUpdate.class)
    String name, 

    @NotBlank(groups = OnUpdate.class)  
    @Email(groups = OnUpdate.class)
    String email, 

    @Pattern(regexp = "^\\+?[0-9]{9,13}$", groups = OnUpdate.class)
    String phoneNumber, 

    @Pattern(regexp = "^\\+?[0-9]{9,13}$", groups = OnUpdate.class)
    String socialSecurityNumber, 

    @Past(groups = OnUpdate.class)
    LocalDate dateOfBirth,

    @NotNull(groups = OnUpdate.class) 
    Gender gender, 

    @NotNull(groups = OnUpdate.class) 
    StaffStatus staffStatus, 

    @NotNull(groups = OnUpdate.class)  
    int departmentId,
    LocalDate joinDate
) {
    public interface OnUpdate{}
    public interface OnCreate{}
}
