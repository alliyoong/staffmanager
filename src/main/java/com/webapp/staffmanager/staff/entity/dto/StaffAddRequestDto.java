package com.webapp.staffmanager.staff.entity.dto;

import java.math.BigDecimal;

import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffType;

import jakarta.validation.constraints.NotEmpty;

import static com.webapp.staffmanager.constant.ExceptionConstant.*;

public record StaffAddRequestDto(@NotEmpty(message = EMPTY_STAFF_NAME_ERROR_MSG, groups = OnUpdate.class)String name, 
                                 @NotEmpty(message = EMPTY_STAFF_AGE_ERROR_MSG, groups = OnUpdate.class)  int age, 
                                 @NotEmpty(message = EMPTY_STAFF_GENDER_ERROR_MSG, groups = OnUpdate.class) Gender gender, 
                                 @NotEmpty(message = EMPTY_STAFF_TYPE_ERROR_MSG, groups = OnUpdate.class) StaffType type, 
                                 @NotEmpty(message = EMPTY_DEPARTMENT_ERROR_MSG, groups = OnUpdate.class)  int departmentId, 
                                 int duration, 
                                 BigDecimal salary) {
                                        
    public interface OnUpdate{}
    public interface OnCreate{}
};