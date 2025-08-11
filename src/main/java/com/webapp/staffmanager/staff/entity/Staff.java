package com.webapp.staffmanager.staff.entity;

import java.time.LocalDate;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
public class Staff {
    @Id
    @Column(name = "staff_id")
    private int id;

    @Column(name = "full_name")
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private int age;
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;
    private int titleId;
    private StaffStatus status;
    private LocalDate joinDate;
    
}
