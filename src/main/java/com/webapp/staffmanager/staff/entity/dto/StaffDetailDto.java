package com.webapp.staffmanager.staff.entity.dto;

import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.department.entity.Department;

public record StaffDetailDto(
        int id,
        String name,
        int age,
        Gender gender,
        StaffStatus status,
        Department department
) {
        
}
