package com.webapp.staffmanager.attendance.service.impl;

import static com.webapp.staffmanager.constant.AppResponseStatus.APP_400_CHECKIN;
import static com.webapp.staffmanager.constant.AppResponseStatus.APP_400_CHECKOUT;
import static com.webapp.staffmanager.constant.AppResponseStatus.APP_404_ATTENDANCE;
import static com.webapp.staffmanager.constant.AppResponseStatus.APP_404_STAFF;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.attendance.entity.Attendance;
import com.webapp.staffmanager.attendance.repository.AttendanceRepository;
import com.webapp.staffmanager.attendance.service.AttendanceService;
import com.webapp.staffmanager.constant.AttendanceStatus;
import com.webapp.staffmanager.exception.GeneralException;
import com.webapp.staffmanager.staff.repository.StaffRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StaffRepository staffRepository;
    // private final AttendanceMapper attendanceMapper;

    // @Override
    // public List<Attendance> getList() {
    // return attendanceRepository.findAll();
    // }

    @Override
    public Attendance getAttendance(int staffId) {
        var today = LocalDate.now();
        var attendance = attendanceRepository.findByStaffIdAndWorkDate(staffId, today)
                .orElse(null);
        return attendance;
    }

    @Override
    public void checkIn(int staffId) {
        var target = staffRepository.findById(staffId)
                .orElseThrow(() -> new GeneralException(APP_404_STAFF));

        var dateNow = LocalDate.now();
        var timeNow = LocalTime.now();
        isPossibleCheckedIn(staffId, dateNow);

        Attendance toAdd = new Attendance();
        toAdd.setStaff(target);
        toAdd.setAttendanceStatus(AttendanceStatus.REQUESTED);
        toAdd.setWorkDate(dateNow);
        toAdd.setCheckInTime(timeNow);
        attendanceRepository.save(toAdd);
    }

    @Transactional
    @Override
    public void checkOut(int staffId) {
        var target = staffRepository.findById(staffId)
                .orElseThrow(() -> new GeneralException(APP_404_STAFF));

        var dateNow = LocalDate.now();
        var timeNow = LocalTime.now();
        var toEdit = isPossibleCheckedOut(staffId, dateNow);
        var seconds = Duration.between(toEdit.getCheckInTime(), timeNow).getSeconds();
        toEdit.setTotalHours(new BigDecimal(seconds).divide(new BigDecimal(3600), 2, RoundingMode.HALF_UP));

        toEdit.setCheckOutTime(timeNow);
    }

    private void isPossibleCheckedIn(int staffId, LocalDate date) {
        var attendance = attendanceRepository.findByStaffIdAndWorkDate(staffId, date);
        if (!attendance.isPresent()) {
            return;
        }
        throw new GeneralException(APP_400_CHECKIN);
    }

    private Attendance isPossibleCheckedOut(int staffId, LocalDate date) {
        var attendance = attendanceRepository.findByStaffIdAndWorkDate(staffId, date)
                .orElseThrow(() -> new GeneralException(APP_404_ATTENDANCE));
        if (null == attendance.getCheckOutTime()) {
            return attendance;
        }
        throw new GeneralException(APP_400_CHECKOUT);
    }

    // @Override
    // public void deleteAttendance(int id) {
    // if(attendanceRepository.existsById(id)){
    // throw new GeneralException(APP_404_ATTENDANCE);
    // }

    // attendanceRepository.deleteById(id);
    // }

    // @Override
    // public void editAttendance(int id, AttendanceAddRequestDto dto) {
    // Attendance toEdit = attendanceRepository.findById(id)
    // .orElseThrow(() -> new GeneralException(APP_404_ATTENDANCE));

    // attendanceMapper.updateAttendanceFromDto(dto, toEdit);
    // log.info("Updating dto: {}", dto);
    // log.info("Updating staff: {}", toEdit);
    // attendanceRepository.save(toEdit);
    // }

    // @Override
    // public List<Attendance> searchOnStaffId(int id) {
    // return attendanceRepository.findAllByStaffId(id);
    // }

}
