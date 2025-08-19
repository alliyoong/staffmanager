package com.webapp.staffmanager.attendance_request.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.staffmanager.attendance_request.entity.OverTimeRequest;

public interface OverTimeRequestRepository extends JpaRepository<OverTimeRequest, Integer>{
    
}
