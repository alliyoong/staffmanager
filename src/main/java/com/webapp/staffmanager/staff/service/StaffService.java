package com.webapp.staffmanager.staff.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.entity.dto.AccountViewDto;
import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;
import com.webapp.staffmanager.util.PageResponseDto;

public interface StaffService {
    List<Staff> getStaffList();
    PageResponseDto<StaffDetailDto> getPage(int pageNumber, int pageSize, String searchTerm);
    // Page<Staff> getPage(int pageNumber, int pageSize);
    void addStaff(StaffAddRequestDto dto);
    void deleteStaff(int id);
    void editStaff(int id, StaffAddRequestDto dto);
    List<Staff> searchStaff(String phrase);
    StaffDetailDto getDetail(int id);
    Staff saveToDb(Staff staff);
    // String translate(Locale locale);
    List<StaffStatus> getStaffStatusList();
    List<Gender> getGenderList();
    // AccountViewDto findAccountByStaffId(int id);
    
    // bulk add data for testing purpose right now
    void saveList(List<StaffAddRequestDto> dtoList);

    // attendance mapper need this
    Staff findById(int id);
}