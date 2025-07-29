package com.webapp.staffmanager.department.restcontroller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.staffmanager.department.entity.Department;
import com.webapp.staffmanager.department.entity.Department.OnCreate;
import com.webapp.staffmanager.department.entity.Department.OnUpdate;
import com.webapp.staffmanager.department.entity.dto.DepartmentAddRequestDto;
import com.webapp.staffmanager.department.service.DepartmentService;
import com.webapp.staffmanager.util.HttpResponse;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/department")
@Validated
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping()
    public ResponseEntity<HttpResponse> getList() {
        var data = service.getDeptList();
        return ResponseEntity.ok(HttpResponse.builder()
        .httpStatus(OK)
        .httpStatusCode(OK.value())
        .data(Map.of("deptList",data))
        .build());
    }
    @GetMapping("/{deptName}")
    public ResponseEntity<HttpResponse> search(@PathVariable("deptName") String deptName) {
        var result = service.searchDepartment(deptName);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatus(OK)
                        .httpStatusCode(OK.value())
                        .data(Map.of("department", result))
                        .build()
        );
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> add(@Validated({OnCreate.class, OnUpdate.class}) @RequestBody DepartmentAddRequestDto data) {
        service.addDepartment(data);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatusCode(CREATED.value())
                        .httpStatus(CREATED)
                        .message("Department has been added successfully")
                        .build()
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<HttpResponse> edit(@PathVariable("id") int id, 
                                            @Validated({OnCreate.class, OnUpdate.class}) @RequestBody DepartmentAddRequestDto data) {
        service.editDepartment(id, data);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatusCode(CREATED.value())
                        .httpStatus(CREATED)
                        .message("Department has been updated successfully")
                        .build()
        );
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<HttpResponse> deleteAccount(@PathVariable int id) {
        service.deleteDepartment(id);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .httpStatusCode(NO_CONTENT.value())
                        .httpStatus(NO_CONTENT)
                        .message("Department successfully deleted.")
                        .build()
        );
    }
}
