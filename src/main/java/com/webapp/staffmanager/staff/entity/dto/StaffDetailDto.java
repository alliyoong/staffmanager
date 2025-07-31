package com.webapp.staffmanager.staff.entity.dto;

import java.math.BigDecimal;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffType;

public record StaffDetailDto(
        int id,
        String name,
        int age,
        Gender gender,
        StaffType type,
        Department department,
        int duration,
        BigDecimal salary
) {}
