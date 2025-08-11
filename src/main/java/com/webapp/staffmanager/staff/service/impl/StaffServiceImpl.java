package com.webapp.staffmanager.staff.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.repository.DepartmentRepository;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.StaffFactory;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;
import com.webapp.staffmanager.staff.repository.StaffRepository;
import com.webapp.staffmanager.staff.service.StaffService;
import com.webapp.staffmanager.util.StaffType;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import static com.webapp.staffmanager.constant.AppResponseStatus.*;

@Service
public class StaffServiceImpl implements StaffService{

    @Override
    public List<Staff> getStaffList() {
        return null;
    }
    
    @PostConstruct
    public void init(){
        System.out.println("This bean is being initialized");
    }
    @PreDestroy
    public void finalize(){
        System.out.println("This bean is being destroyed");
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
        // toAdd = StaffFactory.createStaff(dto, ++staticStaffId);

    }

    @Override
    public void deleteStaff(int id) {

    }

    @Override
    public void editStaff(int id, StaffAddRequestDto dto) {

        Staff toAdd = StaffFactory.createStaff(dto, id);
        // if (dto.type().equals(StaffType.INTERN)) {
        //     toAdd = new InternStaff(id, dto.type(), dto.name(), dto.age(), dto.duration(), dto.gender(), dto.departmentId());
        // } else {
        //     toAdd = new FulltimeStaff(id, dto.type(), dto.name(), dto.age(), dto.salary(), dto.gender(), dto.departmentId());
        // }
    }

    @Override
    public List<Staff> searchStaff(String phrase) {
        return null;
    }

    private void isDepartmentExist(int id){
    }

    @Override
    public StaffDetailDto getDetail(int id) {
        // Optional<Staff> toDetail = Optional.ofNullable(staffList.stream()
                                        // .filter(s -> s.getId()==id)
                                        // .findFirst()
                                        // .orElseThrow(() -> new GeneralException(APP_404_STAFF)));
        // Department department = deptList.stream()
                                            // .filter(s -> s.getDepartmentId() == toDetail.get().getDepartmentId())
                                            // .findFirst().get();
        // if (toDetail.get().getType().equals(StaffType.INTERN)){
        //     var result = (InternStaff) toDetail.get();
        //     return new StaffDetailDto(result.getId(),
        //                                 result.getName(),
        //                                 result.getAge(),
        //                                 result.getGender(),
        //                                 result.getType(),
        //                                 department,
        //                                 result.getInternDuration(),
        //                                 BigDecimal.valueOf(0));
        // } 
        // else if (toDetail.get().getType().equals(StaffType.FULLTIME)){
        //     var result = (FulltimeStaff) toDetail.get();
        //     return new StaffDetailDto(result.getId(),
        //                                 result.getName(),
        //                                 result.getAge(),
        //                                 result.getGender(),
        //                                 result.getType(),
        //                                 department,
        //                                 0,
        //                                 result.getSalary());
        // }
        throw new GeneralException(APP_404_STAFF);
    }
}
