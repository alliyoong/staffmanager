package com.webapp.staffmanager.account.service.impl;

import static com.webapp.staffmanager.constant.AppResponseStatus.APP_404_ACCOUNT;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.account.entity.Account;
import com.webapp.staffmanager.account.entity.dto.AccountViewDto;
import com.webapp.staffmanager.account.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.account.entity.mapper.AccountMapper;
import com.webapp.staffmanager.account.repository.AccountRepository;
import com.webapp.staffmanager.account.repository.AccountSpecification;
import com.webapp.staffmanager.account.service.AccountService;
import com.webapp.staffmanager.constant.AccountStatus;
import com.webapp.staffmanager.constant.AppResponseStatus;
import com.webapp.staffmanager.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder encoder;

    @Override
    public Account findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(AppResponseStatus.APP_404_ACCOUNT));
    }

    @Override
    public void saveToDb(RegisterRequestDto account) {
        var toAdd = accountMapper.toAccount(account);
        toAdd.setPassword(encoder.encode(toAdd.getPassword()));
        toAdd.setCreatedAt(LocalDateTime.now());
        log.info("Account info: {}",toAdd.toString());
        repository.save(toAdd);
    }

    @Override
    public Account findWithStaff(String username) {
        return repository.findAll(AccountSpecification.hasStaff(username)).get(0);
    }
    
    @Override
    public void editAccount(int id, RegisterRequestDto data) {
        log.info("data: {}", data);
        //check if exists
        Account toEdit = repository.findById(id)
                .orElseThrow(() -> new GeneralException(APP_404_ACCOUNT));
        
        accountMapper.updateAccountFromDto(data, toEdit);
        if (data.password() != null && !data.password().isBlank()) {
            toEdit.setPassword(encoder.encode(data.password()));
        }
        log.info("Editing account {}", toEdit);
        
        repository.save(toEdit);
    }
    
    @Override
    public List<AccountStatus> getAccountStatusList() {
        return Arrays.asList(AccountStatus.values());
    }

    @Override
    public AccountViewDto findAccountByStaffId(int id) {
        var found = repository.findByStaffId(id).orElse(null);
        return accountMapper.fromAccount(found);
    }

}
