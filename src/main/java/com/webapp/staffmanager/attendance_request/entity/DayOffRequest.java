package com.webapp.staffmanager.attendance_request.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class DayOffRequest {
    @Id
    @Column(name = "request_id")
    private int requestId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String documentUrl;
    
    @MapsId
    @OneToOne
    @JoinColumn(name = "request_id")
    private AttendanceRequest attendanceRequest;
}
