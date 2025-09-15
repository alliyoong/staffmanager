package com.webapp.staffmanager.account.restcontroller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.account.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.account.entity.dto.RegisterRequestDto.OnCreate;
import com.webapp.staffmanager.account.service.AccountService;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@Slf4j
public class AccountRestController {
    private final AccountService service;

    @PostMapping()
    public HttpResponse register(@Validated({ OnCreate.class}) @RequestBody RegisterRequestDto data) {
        log.info("Adding account: {}", data.toString());
        service.saveToDb(data);
        return HttpResponse.created();
    }

    @PutMapping(path = "/{id}")
    public HttpResponse edit(@PathVariable("id") int id,
            @Validated({ OnCreate.class }) @RequestBody RegisterRequestDto data) {
        service.editAccount(id, data);
        return HttpResponse.created();
    }

    // @DeleteMapping("/{id}")
    // public HttpResponse deleteAccount(@PathVariable int id) {
    //     service.deleteStaff(id);
    //     return HttpResponse.noContent();
    // }
    //

    @GetMapping("/status")
    public HttpResponse getStatusList() {
        var result = service.getAccountStatusList();
        return HttpResponse.ok(result);
    }

    @GetMapping("/check-has-account/{staffId}")
    public HttpResponse checkHasAccount(@PathVariable int staffId) {
        var result = service.findAccountByStaffId(staffId);
        return HttpResponse.ok(result);
    }
    
}
