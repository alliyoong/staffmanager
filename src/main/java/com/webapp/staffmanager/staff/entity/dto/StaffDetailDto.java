package com.webapp.staffmanager.staff.entity.dto;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffStatus;

public record StaffDetailDto(
        int id,
        String name,
        int age,
        Gender gender,
        StaffStatus status,
        Department department
) {
        
}
