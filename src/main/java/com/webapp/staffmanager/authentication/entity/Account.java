package com.webapp.staffmanager.authentication.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.webapp.staffmanager.constant.AccountStatus;
import com.webapp.staffmanager.staff.entity.Staff;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "app_account")
@Getter @Setter
@ToString(exclude = {"staff"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    @JsonBackReference
    private Staff staff;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus accountStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginDate;
    private LocalDateTime lastLoginDateDisplay;

}
