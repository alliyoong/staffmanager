package com.webapp.staffmanager.staff.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.staffmanager.staff.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>{
    List<Staff> findByNameContainingIgnoreCase(String phrase);
}
