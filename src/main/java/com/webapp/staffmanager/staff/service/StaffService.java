package com.webapp.staffmanager.staff.service;

import java.util.List;

import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;

public interface StaffService {
    List<Staff> getStaffList();
    void addStaff(StaffAddRequestDto dto);
    void deleteStaff(int id);
    void editStaff(int id, StaffAddRequestDto dto);
    List<Staff> searchStaff(String phrase);
    StaffDetailDto getDetail(int id);
    // String translate(Locale locale);
    
    // bulk add data for testing purpose right now
    void saveList(List<StaffAddRequestDto> dtoList);
}