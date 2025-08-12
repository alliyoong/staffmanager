package com.webapp.staffmanager.staff.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.util.Gender;
import com.webapp.staffmanager.util.StaffStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
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
    private StaffStatus status;
    @CreationTimestamp
    private LocalDate joinDate;
    
}
