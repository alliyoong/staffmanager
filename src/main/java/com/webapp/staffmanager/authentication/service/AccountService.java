package com.webapp.staffmanager.authentication.service;

import com.webapp.staffmanager.authentication.entity.Account;

public interface AccountService {
    Account findByUsername(String username);
    void save(Account account);
}
