package com.webapp.staffmanager.staff.restcontroller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto.OnCreate;
import com.webapp.staffmanager.authentication.entity.dto.RegisterRequestDto;
import com.webapp.staffmanager.staff.entity.Staff;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto;
import com.webapp.staffmanager.staff.entity.dto.StaffAddRequestDto.OnUpdate;
import com.webapp.staffmanager.staff.service.StaffService;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@Validated
public class StaffRestController {
    private final StaffService service;

    @GetMapping()
    public HttpResponse<List<Staff>> getStaffList() {
        List<Staff> data = service.getStaffList();
        return HttpResponse.ok(data);
    }

    @GetMapping(params = { "page", "size", "search" })
    public HttpResponse getPage(
            @RequestParam(required = true, defaultValue = "0") int page,
            @RequestParam(required = true, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "") String search
            ) {
        var result = service.getPage(page, size, search);
        return HttpResponse.ok(result);
    }

    @GetMapping("/search")
    public HttpResponse search(@RequestParam(name = "name", required = false, defaultValue = "") String staffName) {
        var result = service.searchStaff(staffName);
        return HttpResponse.ok(result);
    }

    @GetMapping("/detail/{id}")
    public HttpResponse getDetail(@PathVariable("id") int id) {
        var result = service.getDetail(id);
        return HttpResponse.ok(result);
    }

    @PostMapping()
    public HttpResponse add(@Validated({ OnCreate.class, OnUpdate.class }) @RequestBody StaffAddRequestDto data) {
        log.info("Adding staff: {}", data.toString());
        service.addStaff(data);
        return HttpResponse.created();
    }

    @PutMapping(path = "/{id}")
    public HttpResponse edit(@PathVariable("id") int id,
            @Validated({ OnCreate.class, OnUpdate.class }) @RequestBody StaffAddRequestDto data) {
        service.editStaff(id, data);
        return HttpResponse.created();
    }

    @DeleteMapping("/{id}")
    public HttpResponse deleteAccount(@PathVariable int id) {
        service.deleteStaff(id);
        return HttpResponse.noContent();
    }
    
    @GetMapping("/check-has-account/{id}")
    public HttpResponse checkHasAccount(@PathVariable int id) {
        var result = service.findAccountByStaffId(id);
        return HttpResponse.ok(result);
    }

    // for testing purpose
    @PostMapping("/batch-insert")
    public HttpResponse saveAll(@RequestBody List<StaffAddRequestDto> dtoList) {
        service.saveList(dtoList);
        return HttpResponse.noContent();
    }

    @GetMapping("/status")
    public HttpResponse getStatusList() {
        var result = service.getStaffStatusList();
        return HttpResponse.ok(result);
    }

    @GetMapping("/gender")
    public HttpResponse getGenderList() {
        var result = service.getGenderList();
        return HttpResponse.ok(result);
    }
}
