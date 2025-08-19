package com.webapp.staffmanager.attendance_request.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.staffmanager.attendance_request.entity.AdjustmentRequest;

public interface AdjustmentRequestRepository extends JpaRepository<AdjustmentRequest, Integer>{
    
}
