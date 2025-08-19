package com.webapp.staffmanager.attendance.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.webapp.staffmanager.attendance.entity.Attendance;
import com.webapp.staffmanager.attendance.entity.dto.AttendanceAddRequestDto;
import com.webapp.staffmanager.staff.service.StaffService;

@Mapper(componentModel = "spring", uses = { StaffService.class})
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "staffId", target = "staff")
    Attendance toAttendance(AttendanceAddRequestDto attendanceAddReuqestDto);

    @Mapping(source = "staffId", target = "staff")
    void updateAttendanceFromDto(AttendanceAddRequestDto dto, @MappingTarget Attendance attendance);
    
    
}