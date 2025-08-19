package com.webapp.staffmanager.attendance.service.impl;

import static com.webapp.staffmanager.constant.AppResponseStatus.APP_404_ATTENDANCE;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.attendance.entity.Attendance;
import com.webapp.staffmanager.attendance.entity.dto.AttendanceAddRequestDto;
import com.webapp.staffmanager.attendance.entity.mapper.AttendanceMapper;
import com.webapp.staffmanager.attendance.repository.AttendanceRepository;
import com.webapp.staffmanager.attendance.service.AttendanceService;
import com.webapp.staffmanager.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService{
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> getList() {
        return attendanceRepository.findAll();
    }

    @Override
    public void addAttendance(AttendanceAddRequestDto dto) {
        Attendance toAdd = attendanceMapper.toAttendance(dto);
        log.info("Adding dto: {}", dto);
        log.info("Adding staff: {}", toAdd);
        attendanceRepository.save(toAdd);
    }

    @Override
    public void deleteAttendance(int id) {
        if(attendanceRepository.existsById(id)){
            throw new GeneralException(APP_404_ATTENDANCE);
        }

        attendanceRepository.deleteById(id);
    }

    @Override
    public void editAttendance(int id, AttendanceAddRequestDto dto) {
        Attendance toEdit = attendanceRepository.findById(id)
                                    .orElseThrow(() -> new GeneralException(APP_404_ATTENDANCE));

        attendanceMapper.updateAttendanceFromDto(dto, toEdit);
        log.info("Updating dto: {}", dto);
        log.info("Updating staff: {}", toEdit);
        attendanceRepository.save(toEdit);
    }

    @Override
    public List<Attendance> searchOnStaffId(int id) {
        return attendanceRepository.findAllByStaffId(id);
    }
    
}
