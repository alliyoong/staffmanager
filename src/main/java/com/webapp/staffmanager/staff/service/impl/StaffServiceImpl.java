package com.webapp.staffmanager.staff.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.exception.ResourceNotFoundException;
import com.webapp.staffmanager.staff.entity.FulltimeStaff;
import com.webapp.staffmanager.staff.entity.InternStaff;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.service.StaffService;
import com.webapp.staffmanager.util.StaffType;

@Service
public class StaffServiceImpl implements StaffService{
    private final List<Staff> staffList = new ArrayList<>();
    public static int staticStaffId = 0;

    @Override
    public List<Staff> getStaffList() {
        return staffList;
    }

    @Override
    public void addStaff(StaffAddRequestDto dto) {
        Staff toAdd;
        if (dto.type().equals(StaffType.INTERN)) {
            toAdd = new InternStaff(++staticStaffId, dto.type(), dto.name(), dto.age(), dto.duration(), dto.gender(), dto.departmentId());
        } else {
            toAdd = new FulltimeStaff(++staticStaffId, dto.type(), dto.name(), dto.age(), dto.salary(), dto.gender(), dto.departmentId());
        }

        staffList.add(toAdd);
    }

    @Override
    public void deleteStaff(int id) {
        isStaffListEmpty();

        Optional<Staff> toDelete = staffList.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (toDelete.isEmpty()) {
            throw new ResourceNotFoundException("staff", "id", id);
        }

        staffList.remove(toDelete.get());
    }

    @Override
    public void editStaff(int id, StaffAddRequestDto dto) {
        isStaffListEmpty();

        Optional<Staff> toEdit = staffList.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (toEdit.isEmpty()) {
            throw new ResourceNotFoundException("staff", "id", id);
        }
        int toEditIndex = staffList.indexOf(toEdit.get());

        Staff toAdd;
        if (dto.type().equals(StaffType.INTERN)) {
            toAdd = new InternStaff(++staticStaffId, dto.type(), dto.name(), dto.age(), dto.duration(), dto.gender(), dto.departmentId());
        } else {
            toAdd = new FulltimeStaff(++staticStaffId, dto.type(), dto.name(), dto.age(), dto.salary(), dto.gender(), dto.departmentId());
        }
        staffList.set(toEditIndex, toAdd);
    }

    @Override
    public List<Staff> searchStaff(String phrase) {
        isStaffListEmpty();
        var resultList = staffList.stream()
                .filter(s -> s.getName().toLowerCase(Locale.ROOT).contains(phrase))
                .toList();
        return resultList;
    }

    private void isStaffListEmpty(){
        if (staffList.isEmpty()) {
            throw new RuntimeException("Staff list is empty");
        }
    }
}
