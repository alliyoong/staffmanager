package com.webapp.staffmanager.authentication.service.impl;

import static com.webapp.staffmanager.constant.AppResponseStatus.APP_404_ACCOUNT;

import org.springframework.stereotype.Service;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.repository.AccountRepository;
import com.webapp.staffmanager.authentication.repository.AccountSpecification;
import com.webapp.staffmanager.authentication.service.AccountService;
import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.exception.GeneralException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(AppResponseStatus.APP_404_ACCOUNT));
    }

    @Override
    public void saveToDb(Account account) {
        repository.save(account);
    }

    @Override
    public Account findWithStaff(String username) {
        // return repository.findOne(AccountSpecification.hasStaff(username))
        //         .orElseThrow(() -> new GeneralException(APP_404_ACCOUNT));
        return repository.findAll(AccountSpecification.hasStaff(username)).get(0);
    }

}
