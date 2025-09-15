package com.webapp.staffmanager.attendance_request.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.webapp.staffmanager.attendance_request.entity.AttendanceRequest;
import com.webapp.staffmanager.attendance_request.entity.DayOffRequest;
import com.webapp.staffmanager.attendance_request.entity.dto.DayOffAddRequestDto;
import com.webapp.staffmanager.constant.AttendanceRequestStatus;
import com.webapp.staffmanager.constant.AttendanceRequestType;
import com.webapp.staffmanager.staff.service.StaffService;

@Mapper(componentModel = "spring", imports = {AttendanceRequestType.class, AttendanceRequestStatus.class}, uses = { StaffService.class})
public interface DayOffRequestMapper {
    DayOffRequestMapper INSTANCE = Mappers.getMapper(DayOffRequestMapper.class);
    
    @Mapping(source = "fromDate", target = "startDate")
    @Mapping(source = "toDate", target = "endDate")
    DayOffRequest fromAddDto(DayOffAddRequestDto dto);

    @Mapping(target = "type", expression = "java(AttendanceRequestType.DAY_OFF)")
    @Mapping(target = "status", expression = "java(AttendanceRequestStatus.PENDING)")
    @Mapping(source = "staffId", target = "staff")
    AttendanceRequest fromDtoToAttendanceRequest(DayOffAddRequestDto dto);
}
