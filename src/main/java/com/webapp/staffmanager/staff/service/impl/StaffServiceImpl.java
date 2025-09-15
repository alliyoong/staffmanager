package com.webapp.staffmanager.staff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.entity.dto.AccountViewDto;
import com.webapp.staffmanager.account.entity.mapper.AccountMapper;
import com.webapp.staffmanager.account.repository.AccountRepository;
import com.webapp.staffmanager.account.service.AccountService;
import com.webapp.staffmanager.attendance.repository.AttendanceRepository;
import com.webapp.staffmanager.constant.Gender;
import com.webapp.staffmanager.constant.StaffStatus;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;
import com.webapp.staffmanager.staff.entity.mapper.StaffMapper;
import com.webapp.staffmanager.staff.repository.StaffRepository;
import com.webapp.staffmanager.staff.repository.StaffSpecifications;
import com.webapp.staffmanager.staff.service.StaffService;
import com.webapp.staffmanager.util.PageResponseDto;

import io.jsonwebtoken.lang.Arrays;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.webapp.staffmanager.constant.AppResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final AttendanceRepository attendanceRepository;
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    // private final AccountService accountService;

    @Override
    public List<Staff> getStaffList() {
        return staffRepository.findAll();
    }

    @Override
    public PageResponseDto<StaffDetailDto> getPage(int pageNumber, int pageSize, String term) {
        Pageable sortedPageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        Specification<Staff> spec;
        if (term != null && !term.isEmpty()) {
            spec = StaffSpecifications.hasAccount(term);
        } else {
            spec = StaffSpecifications.hasAccount();
        }
        Page<Staff> staffPage = staffRepository.findAll(spec, sortedPageable);
        var staffDtos = staffPage.getContent().stream()
                .map(staffMapper::fromStaff)
                .toList();
        return new PageResponseDto<StaffDetailDto>(
                staffDtos,
                staffPage.getNumber(),
                staffPage.getSize(),
                staffPage.getTotalElements(),
                staffPage.getTotalPages(),
                staffPage.isLast());
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

        // delete all attendance records associated with the staff
        attendanceRepository.deleteAllByStaffId(id);

        staffRepository.delete(toDelete);
    }

    @Override
    public void editStaff(int id, StaffAddRequestDto dto) {
        Staff toEdit = staffRepository.findById(id)
                .orElseThrow(() -> new GeneralException(APP_404_STAFF));

        staffMapper.updateStaffFromDto(dto, toEdit);
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
        // // Optional<Staff> toDetail = Optional.ofNullable(staffList.stream()
        // // .filter(s -> s.getId()==id)
        // // .findFirst()
        // // .orElseThrow(() -> new GeneralException(APP_404_STAFF)));
        // // Department department = deptList.stream()
        // // .filter(s -> s.getDepartmentId() == toDetail.get().getDepartmentId())
        // // .findFirst().get();
        // // if (toDetail.get().getType().equals(StaffType.INTERN)){
        // // var result = (InternStaff) toDetail.get();
        // // return new StaffDetailDto(result.getId(),
        // // result.getName(),
        // // result.getAge(),
        // // result.getGender(),
        // // result.getType(),
        // // department,
        // // result.getInternDuration(),
        // // BigDecimal.valueOf(0));
        // // }
        // // else if (toDetail.get().getType().equals(StaffType.FULLTIME)){
        // // var result = (FulltimeStaff) toDetail.get();
        // // return new StaffDetailDto(result.getId(),
        // // result.getName(),
        // // result.getAge(),
        // // result.getGender(),
        // // result.getType(),
        // // department,
        // // 0,
        // result.getSalary());
        // }
        throw new GeneralException(APP_404_STAFF);
    }

    // bulk add data for testing purpose right now
    @Transactional
    @Override
    public void saveList(List<StaffAddRequestDto> dtoList) {
        staffRepository.saveAll(dtoList.stream()
                .map(staffMapper::toStaff)
                .toList());
    }

    @Override
    public Staff findById(int id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new GeneralException(APP_404_STAFF));
    }

    @Override
    public List<StaffStatus> getStaffStatusList() {
        return Arrays.asList(StaffStatus.values());
    }

    @Override
    public List<Gender> getGenderList() {
        return Arrays.asList(Gender.values());
    }

    @Override
    public Staff saveToDb(Staff staff) {
        return staffRepository.save(staff);
    }

    // @Override
    // public AccountViewDto findAccountByStaffId(int id) {
    //     return accountService.findAccountByStaffId(id);
    // }
}
