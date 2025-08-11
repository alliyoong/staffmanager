package com.webapp.staffmanager.department.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.staffmanager.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findById(int id);
    List<Department> findByDepartmentNameContainingIgnoreCase(String name);
    boolean existsById(int id);
}
