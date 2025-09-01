package com.webapp.staffmanager.staff.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.webapp.staffmanager.authentication.entity.Account;
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

    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "account.staff.id", target = "staffId")
    @Mapping(source = "account.staff.name", target = "name")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "account.staff.email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "account.staff.phoneNumber", target = "phoneNumber")
    @Mapping(source = "account.staff.socialSecurityNumber", target = "socialSecurityNumber")
    @Mapping(source = "account.staff.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "account.staff.gender", target = "gender")
    @Mapping(source = "account.staff.staffStatus", target = "status")
    @Mapping(source = "account.staff.joinDate", target = "joinDate")
    @Mapping(source = "account.staff.department", target = "department")
    StaffDetailDto fromAccount(Account account);
}

