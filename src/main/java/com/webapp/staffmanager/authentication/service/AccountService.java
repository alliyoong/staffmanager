package com.webapp.staffmanager.authentication.service;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.repository.AccountSpecification;

public interface AccountService {
    Account findByUsername(String username);
    void saveToDb(Account account);
    Account findWithStaff(String username);
}
