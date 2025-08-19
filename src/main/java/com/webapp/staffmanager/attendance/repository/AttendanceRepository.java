package com.webapp.staffmanager.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.staffmanager.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    void deleteAllByStaffId(int id);
    List<Attendance> findAllByStaffId(int id);
}
