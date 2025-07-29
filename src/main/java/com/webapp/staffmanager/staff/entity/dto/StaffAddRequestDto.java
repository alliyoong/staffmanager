package com.webapp.staffmanager.staff.entity.dto;

import java.math.BigDecimal;

import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffType;

public record StaffAddRequestDto(String name, int age, Gender gender, StaffType type, int departmentId, int duration, BigDecimal salary) {};