package com.webapp.staffmanager.staff.entity.dto;

import java.time.LocalDate;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.department.entity.Department;

public record StaffDetailDto(
                int accountId,
                int staffId,
                String username,
                String password,
                String name,
                String email,
                String phoneNumber,
                String socialSecurityNumber,
                LocalDate dateOfBirth,
                Department department,
                Gender gender,
                StaffStatus status,
                LocalDate joinDate

) {

}
