package com.webapp.staffmanager.department.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import static com.webapp.staffmanager.constant.ExceptionConstant.*;

import com.webapp.staffmanager.util.validator.AppNotBlank;

public record DepartmentAddRequestDto ( 
    // @NotEmpty(message = EMPTY_NAME_ERROR_MSG, groups = OnUpdate.class)
    // @NotBlank(groups = OnUpdate.class)
    @AppNotBlank(groups = OnUpdate.class)
    String name, 
    @NotEmpty(message = EMPTY_DESCRIPTION_ERROR_MSG, groups = OnUpdate.class)
    String description
){
    public interface OnUpdate{}
    public interface OnCreate{}
};

    