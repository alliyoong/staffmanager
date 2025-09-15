package com.webapp.staffmanager.account.service;

import java.util.List;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.entity.dto.AccountViewDto;
import com.webapp.staffmanager.account.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.constant.AccountStatus;

public interface AccountService {
    Account findByUsername(String username);
    void saveToDb(RegisterRequestDto account);
    Account findWithStaff(String username);
    void editAccount(int id, RegisterRequestDto data);
    List<AccountStatus> getAccountStatusList();
    AccountViewDto findAccountByStaffId(int id);
}
