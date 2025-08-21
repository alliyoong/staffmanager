package com.webapp.staffmanager.authentication.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.webapp.staffmanager.constant.AccountStatus;
import com.webapp.staffmanager.staff.entity.Staff;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "app_account")
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginDate;
    private LocalDateTime lastLoginDateDisplay;

}
