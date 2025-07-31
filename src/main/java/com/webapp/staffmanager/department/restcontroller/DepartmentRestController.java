package com.webapp.staffmanager.department.restcontroller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto.OnCreate;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto.OnUpdate;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.util.HttpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/department")
@Validated
public class DepartmentRestController {
    private final DepartmentService service;

    @GetMapping()
    public HttpResponse getList() {
        var data = service.getDeptList();
        return HttpResponse.ok(data);
    }
    @GetMapping("/{deptName}")
    public HttpResponse search(@PathVariable("deptName") String deptName) {
        var result = service.searchDepartment(deptName);
        return HttpResponse.ok(result);
    }

    @PostMapping()
    public HttpResponse add(@Validated({OnCreate.class, OnUpdate.class}) @RequestBody DepartmentAddRequestDto data) {
        service.addDepartment(data);
        return HttpResponse.created();
    }

    @PutMapping(path = "/{id}")
    public HttpResponse edit(@PathVariable("id") int id, 
                            @Validated({OnCreate.class, OnUpdate.class}) @RequestBody DepartmentAddRequestDto data) {
        service.editDepartment(id, data);
        return HttpResponse.created();
    }

    @DeleteMapping (path = "/{id}")
    public HttpResponse delete(@PathVariable int id) {
        service.deleteDepartment(id);
        return HttpResponse.noContent();
    }
}
