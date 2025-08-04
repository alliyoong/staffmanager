package com.webapp.staffmanager.department.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;
import com.webapp.staffmanager.department.entity.dto.DepartmentDetailDto;
import com.webapp.staffmanager.department.repository.DepartmentRepository;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.exception.GeneralException;
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
                throw new GeneralException(APP_400_DEPT);
            }
        }

        deptList.remove(toDelete.get());
    }

    @Override
    public void editDepartment(int id, DepartmentAddRequestDto dto) {
        isDeptListEmpty();

        Optional<Department> toEdit = Optional.ofNullable(deptList.stream()
                                                            .filter(s -> s.getDepartmentId() == id)
                                                            .findFirst()
                                                            .orElseThrow(() -> new GeneralException(APP_404_DEPT)));
        int toEditIndex = deptList.indexOf(toEdit.get());
        Department toAdd = new Department(id, dto.name(), dto.description());
        deptList.set(toEditIndex, toAdd);
    }

    @Override
    public List<Department> searchDepartment(String phrase) {
        isDeptListEmpty();
        return deptList.stream()
                .filter(s -> s.getName().toLowerCase().contains(phrase))
                .toList();
    }
    
    private void isDeptListEmpty(){
        if (deptList.isEmpty()) {
            throw new GeneralException(APP_404_DEPT_LIST);
        }
    }

    @Override
    public DepartmentDetailDto getDetail(int id) {
        Optional<Department> toDetail = Optional.ofNullable(deptList.stream()
                                        .filter(s -> s.getDepartmentId()==id)
                                        .findFirst()
                                        .orElseThrow(() -> new GeneralException(APP_404_DEPT)));
        List<Staff> staffInDept = staffList.stream()
                                            .filter(s -> s.getDepartmentId() == id)
                                            .collect(Collectors.toList());
        return new DepartmentDetailDto(toDetail.get().getDepartmentId(),
                                        toDetail.get().getName(),
                                        toDetail.get().getDescription(),
                                        staffInDept);
    }
}