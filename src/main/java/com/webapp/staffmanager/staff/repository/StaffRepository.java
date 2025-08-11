package com.webapp.staffmanager.staff.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.staffmanager.staff.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
}
