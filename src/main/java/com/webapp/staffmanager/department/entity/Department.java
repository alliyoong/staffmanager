package com.webapp.staffmanager.department.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webapp.staffmanager.staff.entity.Staff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter @Setter
@Entity
public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;
    // @Column(name = "department_name")
    private String departmentName;
    // @Column(name = "department_description")
    private String departmentDescription;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Staff> staffList;

    public Department() {}
    public Department(String name, String description) {
        this.departmentName = name;
        this.departmentDescription = description;
    }

}
