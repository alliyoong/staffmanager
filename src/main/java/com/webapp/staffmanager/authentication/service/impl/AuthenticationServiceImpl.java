package com.webapp.staffmanager.authentication.service.impl;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.webapp.staffmanager.authentication.entity.Account;
import com.webapp.staffmanager.authentication.entity.UserPrincipal;
import com.webapp.staffmanager.authentication.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.authentication.entity.mapper.AccountMapper;
import com.webapp.staffmanager.authentication.repository.AccountSpecification;
import com.webapp.staffmanager.authentication.service.AccountService;
import com.webapp.staffmanager.authentication.service.AuthenticationService;
import com.webapp.staffmanager.authentication.service.JwtTokenService;
import com.webapp.staffmanager.constant.AccountStatus;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.mapper.StaffMapper;
import com.webapp.staffmanager.staff.service.StaffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountService accountService;
    private final AuthenticationManager appAuthenticationManager;
    private final JwtTokenService jwtService;
    private final PasswordEncoder encoder;
    private final AccountMapper accountMapper;
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @Transactional
    @Override
    public void register(RegisterRequestDto data) {
        Staff toAddStaff = accountMapper.toStaff(data);
        log.info("Staff info: {}",toAddStaff.toString());
        var added = staffService.saveToDb(toAddStaff);

        Account toAddAccount = accountMapper.toAccount(data);
        log.info("Account info: {}",toAddAccount.toString());
        toAddAccount.setPassword(encoder.encode(toAddAccount.getPassword()));
        toAddAccount.setStaff(added);
        toAddAccount.setAccountStatus(AccountStatus.ENABLED);
        accountService.saveToDb(toAddAccount);
    }

    @Transactional
    @Override
    public void editAccount(int id, RegisterRequestDto data) {
        //check if exists
        staffService.findById(id);

        Staff toEditStaff = accountMapper.toStaff(data);
        Account toEdiAccount = accountMapper.toAccount(data);
        toEdiAccount.setAccountStatus(AccountStatus.ENABLED);
        toEdiAccount.setStaff(toEditStaff);
        toEdiAccount.setPassword(encoder.encode(toEdiAccount.getPassword()));
        toEditStaff.setId(id);

        staffService.saveToDb(toEditStaff);
        accountService.saveToDb(toEdiAccount);

    }

    @Transactional
    @Override
    public Map<String, Object> login(String username, String password, HttpServletResponse response,
            HttpServletRequest request) {
        appAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var loginAccount = accountService.findByUsername(username);
        // Account acc = accountService.findWithStaff(username);
        UserPrincipal user = new UserPrincipal(loginAccount);
        var token = jwtService.generateAccessToken(user);
        var dto = staffMapper.fromAccount(loginAccount);

        log.info("Login account: {}", dto.toString());
        return Map.of("token", token, "staff", dto);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }
    
}
