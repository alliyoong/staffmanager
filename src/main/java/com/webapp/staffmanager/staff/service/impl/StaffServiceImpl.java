package com.webapp.staffmanager.staff.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.repository.DepartmentRepository;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.entity.FulltimeStaff;
import com.webapp.staffmanager.staff.entity.InternStaff;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.StaffFactory;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;
import com.webapp.staffmanager.staff.repository.StaffRepository;
import com.webapp.staffmanager.staff.service.StaffService;
import com.webapp.staffmanager.util.StaffType;
import static com.webapp.staffmanager.constant.AppResponseStatus.*;


@Service
public class StaffServiceImpl implements StaffService{
    private final List<Staff> staffList = StaffRepository.staffList;
    private final List<Department> deptList = DepartmentRepository.deptList;
    public static int staticStaffId = 0;

    @Override
    public List<Staff> getStaffList() {
        return staffList;
    }

    @Override
    public void addStaff(StaffAddRequestDto dto) {
        Staff toAdd;
        isDepartmentExist(dto.departmentId());
        // if (dto.type().equals(StaffType.INTERN)) {
        //     // toAdd = new InternStaff(++staticStaffId, dto.type(), dto.name(), dto.age(), dto.duration(), dto.gender(), dto.departmentId());
        //     toAdd = new Staff
        //             .InternStaffBuilder(++staticStaffId, dto.name(),dto.type(), dto.departmentId(), dto.duration())
        //             .age(dto.age())
        //             .gender(dto.gender())
        //             .build();
        // } else {
        //     toAdd = new FulltimeStaff(++staticStaffId, dto.type(), dto.name(), dto.age(), dto.salary(), dto.gender(), dto.departmentId());
        // }
        toAdd = StaffFactory.createStaff(dto, ++staticStaffId);

        staffList.add(toAdd);
    }

    @Override
    public void deleteStaff(int id) {
        isStaffListEmpty();

        Optional<Staff> toDelete = staffList.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (toDelete.isEmpty()) {
            // throw new ResourceNotFoundException("staff", "id", id);
            throw new GeneralException(APP_404_STAFF);
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
            throw new GeneralException(APP_404_STAFF);
        }
        int toEditIndex = staffList.indexOf(toEdit.get());

        Staff toAdd = StaffFactory.createStaff(dto, id);
        // if (dto.type().equals(StaffType.INTERN)) {
        //     toAdd = new InternStaff(id, dto.type(), dto.name(), dto.age(), dto.duration(), dto.gender(), dto.departmentId());
        // } else {
        //     toAdd = new FulltimeStaff(id, dto.type(), dto.name(), dto.age(), dto.salary(), dto.gender(), dto.departmentId());
        // }
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
            throw new GeneralException(APP_404_STAFF_LIST);
        }
    }
    private void isDepartmentExist(int id){
        deptList.stream()
            .mapToInt(d -> d.getDepartmentId())
            .filter(d -> d == id)
            .findFirst()
            .orElseThrow(() -> new GeneralException(APP_404_DEPT));
    }

    @Override
    public StaffDetailDto getDetail(int id) {
        Optional<Staff> toDetail = Optional.ofNullable(staffList.stream()
                                        .filter(s -> s.getId()==id)
                                        .findFirst()
                                        .orElseThrow(() -> new GeneralException(APP_404_STAFF)));
        Department department = deptList.stream()
                                            .filter(s -> s.getDepartmentId() == toDetail.get().getDepartmentId())
                                            .findFirst().get();
        if (toDetail.get().getType().equals(StaffType.INTERN)){
            var result = (InternStaff) toDetail.get();
            return new StaffDetailDto(result.getId(),
                                        result.getName(),
                                        result.getAge(),
                                        result.getGender(),
                                        result.getType(),
                                        department,
                                        result.getInternDuration(),
                                        BigDecimal.valueOf(0));
        } 
        else if (toDetail.get().getType().equals(StaffType.FULLTIME)){
            var result = (FulltimeStaff) toDetail.get();
            return new StaffDetailDto(result.getId(),
                                        result.getName(),
                                        result.getAge(),
                                        result.getGender(),
                                        result.getType(),
                                        department,
                                        0,
                                        result.getSalary());
        }
        throw new GeneralException(APP_404_STAFF);
    }
}
