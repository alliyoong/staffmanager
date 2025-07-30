package com.webapp.staffmanager.department.entity.dto;

import jakarta.validation.constraints.NotEmpty;

import static com.webapp.staffmanager.constant.ExceptionConstant.*;

public record DepartmentAddRequestDto ( 
    @NotEmpty(message = EMPTY_DEPARTMENT_NAME_ERROR_MSG, groups = OnUpdate.class)String name, 
    @NotEmpty(message = EMPTY_DEPARTMENT_DESCRIPTION_ERROR_MSG, groups = OnUpdate.class)String description
){
    public interface OnUpdate{}
    public interface OnCreate{}
};

    