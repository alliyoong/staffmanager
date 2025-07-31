package com.webapp.staffmanager.staff.service;

import java.util.List;
import java.util.Locale;

import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;

public interface StaffService {
    List<Staff> getStaffList();
    void addStaff(StaffAddRequestDto dto);
    void deleteStaff(int id);
    void editStaff(int id, StaffAddRequestDto dto);
    List<Staff> searchStaff(String phrase);
    // String translate(Locale locale);
}