package com.webapp.staffmanager.department.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;
import com.webapp.staffmanager.department.repository.DepartmentRepository;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.exception.ResourceAlreadyInUseException;
import com.webapp.staffmanager.exception.ResourceNotFoundException;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.repository.StaffRepository;
import static com.webapp.staffmanager.constant.AppResponseStatus.*;

@Service
public class DepartmentServiceImpl implements DepartmentService { 
    private final List<Department> deptList = DepartmentRepository.deptList;
    private final List<Staff> staffList = StaffRepository.staffList;
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
            throw new GeneralException(APP_404_DEPT);
        } 
        else {
            if (staffList.stream()
                    .mapToInt(s -> s.getDepartmentId())
                    .anyMatch(s -> toDelete.get().getDepartmentId() == s)) {
                throw new GeneralException(APP_404_STAFF_LIST);
            }
        }

        deptList.remove(toDelete.get());
    }

    @Override
    public void editDepartment(int id, DepartmentAddRequestDto dto) {
        isDeptListEmpty();

        Optional<Department> toEdit = deptList.stream()
                .filter(s -> s.getDepartmentId() == id)
                .findFirst();
        if (toEdit.isEmpty()) {
            throw new GeneralException(APP_404_DEPT);
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
            throw new GeneralException(APP_404_DEPT_LIST);
        }
    }
}