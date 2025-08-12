package com.webapp.staffmanager.staff.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;
import com.webapp.staffmanager.staff.entity.mapper.StaffMapper;
import com.webapp.staffmanager.staff.repository.StaffRepository;
import com.webapp.staffmanager.staff.service.StaffService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;

import static com.webapp.staffmanager.constant.AppResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    // private final ModelMapper modelMapper;

    @Override
    public List<Staff> getStaffList() {
        return staffRepository.findAll();
    }
    
    @Override
    public void addStaff(StaffAddRequestDto dto) {
        Staff toAdd = staffMapper.toStaff(dto);
        log.info("Adding dto: {}", dto);
        log.info("Adding staff: {}", toAdd);
        staffRepository.save(toAdd);
    }

    @Override
    public void deleteStaff(int id) {
        Staff toDelete = staffRepository.findById(id)
            .orElseThrow(() -> new GeneralException(APP_404_STAFF));
        staffRepository.delete(toDelete);
    }

    @Override
    public void editStaff(int id, StaffAddRequestDto dto) {
        // Staff toEdit = staffRepository.findById(id)
        //     .orElseThrow(() -> new GeneralException(APP_404_STAFF));
        if (!staffRepository.existsById(id)) {
            throw new GeneralException(APP_404_STAFF);
        }
        Staff toEdit = staffMapper.toStaff(dto);
        log.info("Updating dto: {}", dto);
        log.info("Updating staff: {}", toEdit);
        staffRepository.save(toEdit);
    }

    @Override
    public List<Staff> searchStaff(String phrase) {
        return staffRepository.findByNameContainingIgnoreCase(phrase);
    }

    @Override
    public StaffDetailDto getDetail(int id) {
    //     // Optional<Staff> toDetail = Optional.ofNullable(staffList.stream()
    //                                     // .filter(s -> s.getId()==id)
    //                                     // .findFirst()
    //                                     // .orElseThrow(() -> new GeneralException(APP_404_STAFF)));
    //     // Department department = deptList.stream()
    //                                         // .filter(s -> s.getDepartmentId() == toDetail.get().getDepartmentId())
    //                                         // .findFirst().get();
    //     // if (toDetail.get().getType().equals(StaffType.INTERN)){
    //     //     var result = (InternStaff) toDetail.get();
    //     //     return new StaffDetailDto(result.getId(),
    //     //                                 result.getName(),
    //     //                                 result.getAge(),
    //     //                                 result.getGender(),
    //     //                                 result.getType(),
    //     //                                 department,
    //     //                                 result.getInternDuration(),
    //     //                                 BigDecimal.valueOf(0));
    //     // } 
    //     // else if (toDetail.get().getType().equals(StaffType.FULLTIME)){
    //     //     var result = (FulltimeStaff) toDetail.get();
    //     //     return new StaffDetailDto(result.getId(),
    //     //                                 result.getName(),
    //     //                                 result.getAge(),
    //     //                                 result.getGender(),
    //     //                                 result.getType(),
    //     //                                 department,
    //     //                                 0,
        //                                 result.getSalary());
        // }
        throw new GeneralException(APP_404_STAFF);
    }
    
    // bulk add data for testing purpose right now
    @Transactional
    @Override
    public void saveList(List<StaffAddRequestDto> dtoList){
        staffRepository.saveAll(dtoList.stream()
            .map(staffMapper::toStaff)
            .toList());
    }
}
