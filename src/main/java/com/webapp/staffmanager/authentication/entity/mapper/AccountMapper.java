package com.webapp.staffmanager.authentication.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.service.StaffService;

@Mapper(componentModel = "spring", uses = {DepartmentService.class, StaffService.class})
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "departmentId", target = "department")
    Staff toStaff(RegisterRequestDto dto);

    // @Mapping(target = "accountStatus", ignore = true)
    Account toAccount(RegisterRequestDto dto);
    
}
