package com.webapp.staffmanager.attendance_request.service;

import java.util.List;

import com.webapp.staffmanager.attendance_request.entity.DayOffRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.DayOffAddRequestDto;

public interface DayOffRequestService {
    List<DayOffRequest> getList();
    void add(DayOffAddRequestDto dto);
    void delete(int id);
    void edit(int id, DayOffAddRequestDto dto);
    List<DayOffRequest> searchOnStaffId(int id);
    
}
