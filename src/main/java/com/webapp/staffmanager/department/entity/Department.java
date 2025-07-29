package com.webapp.staffmanager.department.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static com.webapp.staffmanager.constant.ExceptionConstant.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Department {
    @JsonProperty("id")
    private int departmentId;

    @JsonProperty("name")
    @NotEmpty(message = EMPTY_DEPARTMENT_NAME_ERROR_MSG, groups = OnUpdate.class)
    private String name;

    @JsonProperty("description")
    @NotEmpty(message = EMPTY_DEPARTMENT_DESCRIPTION_ERROR_MSG, groups = OnUpdate.class)
    private String description;

    @Override
    public String toString() {
        return name + " - " + departmentId + " - " + description;
    }

    public interface OnUpdate{}
    public interface OnCreate{}
}
