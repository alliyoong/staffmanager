package com.webapp.staffmanager.attendance_request.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webapp.staffmanager.attendance.entity.Attendance;
import com.webapp.staffmanager.constant.AttendanceRequestStatus;
import com.webapp.staffmanager.constant.AttendanceRequestType;
import com.webapp.staffmanager.staff.entity.Staff;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(exclude = "staffId")
public class AttendanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "request_type")
    private AttendanceRequestType type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "request_status")
    private AttendanceRequestStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    @JsonManagedReference
    private Staff staff;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "attendanceRequest", fetch = FetchType.LAZY, optional = true)
    @PrimaryKeyJoinColumn
    private AdjustmentRequest adjustmentRequest;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "attendanceRequest", fetch = FetchType.LAZY, optional = true)
    @PrimaryKeyJoinColumn
    private DayOffRequest dayOffRequest;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "attendanceRequest", fetch = FetchType.LAZY, optional = true)
    @PrimaryKeyJoinColumn
    private OverTimeRequest overTimeRequest;

}
