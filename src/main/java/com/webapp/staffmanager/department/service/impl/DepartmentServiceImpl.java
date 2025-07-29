package com.webapp.staffmanager.department.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.exception.ResourceNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService { 
    private final List<Department> deptList = new ArrayList<>();
    public static int staticDeptId = 0;

    public DepartmentServiceImpl() {
        deptList.add(new Department(++staticDeptId, "Finance", "do finance stuff"));
        deptList.add(new Department(++staticDeptId, "IT", "programming stuff"));
    }

    public List<Department> getDeptList() {
        return deptList;
    }

    @Override
    public void addDepartment(DepartmentAddRequestDto dto) {
        Department toAdd = new Department(++staticDeptId, dto.name(), dto.description());
        deptList.add(toAdd);
    }

    @Override
    public void deleteDepartment(int id) {
        isDeptListEmpty();

        Optional<Department> toDelete = deptList.stream()
                .filter(s -> s.getDepartmentId() == id)
                .findFirst();
        if (toDelete.isEmpty()) {
            throw new ResourceNotFoundException("department", "id", id);
        } 
        //todo -- prevent deleting department with staff
        // else {
            // if (staffList.stream()
                    // .mapToInt(s -> s.getDepartmentId())
                    // .anyMatch(s -> toDelete.get().getDepartmentId() == s)) {
                // printMessage(FAILURE, "Cannot delete this department because it's not empty");
                // return;
            // }
        // }

        deptList.remove(toDelete.get());
    }

    @Override
    public void editDepartment(int id, DepartmentAddRequestDto dto) {
        isDeptListEmpty();

        Optional<Department> toEdit = deptList.stream()
                .filter(s -> s.getDepartmentId() == id)
                .findFirst();
        if (toEdit.isEmpty()) {
            throw new ResourceNotFoundException("department", "id", id);
        }
        int toEditIndex = deptList.indexOf(toEdit.get());
        Department toAdd = new Department(id, dto.name(), dto.description());
        deptList.set(toEditIndex, toAdd);
    }

    @Override
    public List<Department> searchDepartment(String phrase) {
        isDeptListEmpty();
        var resultList = deptList.stream()
                .filter(s -> s.getName().toLowerCase().contains(phrase))
                .toList();
        return resultList;
    }
    
    private void isDeptListEmpty(){
        if (deptList.isEmpty()) {
            throw new RuntimeException("Department list is empty");
        }
    }
}