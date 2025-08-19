package com.webapp.staffmanager.attendance_request.service;

import java.util.List;

import com.webapp.staffmanager.attendance_request.entity.AdjustmentRequest;
import com.webapp.staffmanager.attendance_request.entity.AttendanceRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.AdjustmentAddRequestDto;

public interface AdjustmentRequestService {
    List<AttendanceRequest> getList();
    void add(AdjustmentAddRequestDto dto);
    void delete(int id);
    void edit(int id, AdjustmentAddRequestDto dto);
    List<AdjustmentRequest> searchOnStaffId(int id);
}
