package com.webapp.staffmanager.department.entity.dto;

import java.util.List;

import com.webapp.staffmanager.staff.entity.Staff;

public record DepartmentDetailDto(int id, String name, String description, List<Staff> staffList){}
