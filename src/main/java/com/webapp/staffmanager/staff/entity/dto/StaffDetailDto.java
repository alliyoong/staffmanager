package com.webapp.staffmanager.staff.entity.dto;

import java.time.LocalDate;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.department.entity.Department;

public record StaffDetailDto(
                int staffId,
                Account account,
                String name,
                String email,
                String phoneNumber,
                String socialSecurityNumber,
                LocalDate dateOfBirth,
                Department department,
                Gender gender,
                StaffStatus staffStatus,
                LocalDate joinDate

) {

}
