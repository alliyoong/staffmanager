package com.webapp.staffmanager.attendance_request.service;

import java.util.List;

import com.webapp.staffmanager.attendance_request.entity.OverTimeRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.OverTimeAddRequestDto;

public interface OverTimeRequestService {
    List<OverTimeRequest> getList();
    void add(OverTimeAddRequestDto dto);
    void delete(int id);
    void edit(int id, OverTimeAddRequestDto dto);
    List<OverTimeRequest> searchOnStaffId(int id);
}
