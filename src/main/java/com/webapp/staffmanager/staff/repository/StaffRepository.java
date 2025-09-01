package com.webapp.staffmanager.staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.webapp.staffmanager.staff.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>, JpaSpecificationExecutor<Staff> {
    List<Staff> findByNameContainingIgnoreCase(String phrase);
}
