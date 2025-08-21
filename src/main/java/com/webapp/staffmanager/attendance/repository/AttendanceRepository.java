package com.webapp.staffmanager.attendance.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.staffmanager.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    void deleteAllByStaffId(int id);
    List<Attendance> findAllByStaffId(int id);
    Optional<Attendance> findByStaffIdAndWorkDate(int id, LocalDate date);
}
