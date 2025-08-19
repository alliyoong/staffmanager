package com.webapp.staffmanager.department.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;
import com.webapp.staffmanager.department.entity.dto.DepartmentDetailDto;
import com.webapp.staffmanager.department.repository.DepartmentRepository;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.exception.GeneralException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.webapp.staffmanager.constant.AppResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService { 
    private final DepartmentRepository departmentRepository;

    // public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    //     this.departmentRepository = departmentRepository;
    // }

    // private final StaffRepository staffRepository;

    public List<Department> getDeptList() {
        return departmentRepository.findAll();
    }

    @Override
    public void addDepartment(DepartmentAddRequestDto dto) {
        Department toAdd = new Department(dto.name(), dto.description());
        departmentRepository.save(toAdd);
    }

    @Override
    public void deleteDepartment(int id) {
        var toDelete = departmentRepository.findById(id)
                                    .orElseThrow(() -> new GeneralException(APP_404_DEPT));

        // don't let delete department if it has staff members
        if (!toDelete.getStaffList().isEmpty()) {
            throw new GeneralException(APP_400_DEPT);
        }

        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editDepartment(int id, DepartmentAddRequestDto dto) {
        var toEdit = departmentRepository.findById(id)
                                        .orElseThrow(() -> new GeneralException(APP_404_DEPT));
        if (!(dto.name() == null) && !dto.name().isBlank()) {
            toEdit.setDepartmentName(dto.name());
        }
        if (!(dto.description() == null) && !dto.description().isBlank()) {
            toEdit.setDepartmentDescription(dto.description());
        }
    }

    @Override
    public List<Department> searchDepartment(String phrase) {
        return departmentRepository.findByDepartmentNameContainingIgnoreCase(phrase);
    }
    
    @Override
    public DepartmentDetailDto getDetail(int id) {
        return departmentRepository.findById(id)
            .map(department -> new DepartmentDetailDto(department.getDepartmentId(), department.getDepartmentName(), 
                                                        department.getDepartmentDescription(), 
                                                        department.getStaffList()))
            .orElseThrow(() -> new GeneralException(APP_404_DEPT)) ;
    }

    @Override
    public Department findDepartmentById(int id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new GeneralException(APP_404_DEPT));
    }
}