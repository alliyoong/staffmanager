package com.webapp.staffmanager.attendance.service;

import java.util.List;

import com.webapp.staffmanager.attendance.entity.Attendance;
import com.webapp.staffmanager.attendance.entity.dto.AttendanceAddRequestDto;

public interface AttendanceService {
    // List<Attendance> getList();
    // void addAttendance(int staffId);
    void checkIn(int staffId);
    void checkOut(int staffId);
    Attendance getAttendance(int staffId);
    // void deleteAttendance(int id);
    // // void editAttendance(int id, AttendanceAddRequestDto dto);
    // List<Attendance> searchOnStaffId(int id);
}
