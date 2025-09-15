package com.webapp.staffmanager.account.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.entity.dto.AccountViewDto;
import com.webapp.staffmanager.account.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.staff.service.StaffService;

@Mapper(componentModel = "spring", uses = {DepartmentService.class, StaffService.class})
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    
    @Mapping(source = "staffId", target = "staff")
    Account toAccount(RegisterRequestDto dto);
    
    @Mapping(source = "username", target = "username")
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "accountStatus", target = "accountStatus")
    @Mapping(source = "lastLoginDateDisplay", target = "lastLoginDateDisplay")
    @Mapping(source = "createdAt", target = "createdAt")
    AccountViewDto fromAccount(Account account);

    @Mapping(target = "staff", ignore = true)
    void updateAccountFromDto(RegisterRequestDto dto, @MappingTarget Account account);
    
}
