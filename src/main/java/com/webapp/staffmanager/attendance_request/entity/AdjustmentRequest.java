package com.webapp.staffmanager.attendance_request.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name = "attendance_adjustment_request")
public class AdjustmentRequest {
    @Id
    @Column(name = "request_id")
    private int requestId;

    private LocalDate targetDate;
    private LocalDateTime newCheckInTime;
    private LocalDateTime newCheckOutTime;
    private String reason;
    
    @MapsId
    @OneToOne(mappedBy = "adjustmentRequest")
    @JoinColumn(name = "request_id")
    private AttendanceRequest attendanceRequest;
}
