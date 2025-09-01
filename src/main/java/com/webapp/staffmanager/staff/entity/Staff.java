package com.webapp.staffmanager.staff.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.department.entity.Department;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Entity
@ToString
public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String name;
    private String email;
    private String phoneNumber;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @JsonManagedReference
    private Department department;
    // private int titleId;
    @Column(name = "staff_status")
    @Enumerated(EnumType.STRING)
    private StaffStatus staffStatus;
    private LocalDate joinDate;
    
    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Account account;
}
