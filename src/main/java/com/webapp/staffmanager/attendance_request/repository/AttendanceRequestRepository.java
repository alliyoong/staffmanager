package com.webapp.staffmanager.attendance_request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.staffmanager.attendance_request.entity.AttendanceRequest;
import com.webapp.staffmanager.constant.RequestType;

public interface AttendanceRequestRepository extends JpaRepository<AttendanceRequest, Integer>{
    // List<AttendanceRequest> findAllByRequestType(RequestType type);
}
