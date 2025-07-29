package com.webapp.staffmanager.department.service;

import java.util.List;
import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;

public interface DepartmentService {
    List<Department> getDeptList();
    void addDepartment(DepartmentAddRequestDto dto);
    void deleteDepartment(int id);
    void editDepartment(int id, DepartmentAddRequestDto dto);
    List<Department> searchDepartment(String phrase);
}
