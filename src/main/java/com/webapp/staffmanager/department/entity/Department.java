package com.webapp.staffmanager.department.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Department {
    private int departmentId;
    private String name;
    private String description;

    @Override
    public String toString() {
        return name + " - " + departmentId + " - " + description;
    }

}
