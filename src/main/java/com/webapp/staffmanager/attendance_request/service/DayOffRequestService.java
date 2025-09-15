package com.webapp.staffmanager.attendance_request.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.webapp.staffmanager.attendance_request.entity.DayOffRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.DayOffAddRequestDto;

public interface DayOffRequestService {
    void add(DayOffAddRequestDto dto, MultipartFile document);
    void delete(int id);
    void update(int id, DayOffAddRequestDto dto);
    List<DayOffRequest> searchOnStaffId(int id);
    
}
