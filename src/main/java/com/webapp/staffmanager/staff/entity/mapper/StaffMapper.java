package com.webapp.staffmanager.staff.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffDetailDto;

@Mapper(componentModel = "spring", uses = {DepartmentService.class})
public interface StaffMapper{

    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "departmentId", target = "department")
    @Mapping(target = "joinDate", ignore = true)
    Staff toStaff(StaffAddRequestDto staffAddRequestDto);

    @Mapping(source = "departmentId", target = "department")
    void updateStaffFromDto(StaffAddRequestDto dto, @MappingTarget Staff staff);
    
    StaffDetailDto fromStaff(Staff staff);
}
